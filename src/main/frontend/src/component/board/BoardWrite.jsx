import React,{useState ,useEffect } from 'react'
import axios from 'axios'; //라이브러리 가지고 오기
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

let bcno = 0; // 선택한 카테고리 번호
let bcontent = ''; // 게시물 내용 [ 전역변수 ] // 변수가 수정될경우 재렌더링할 필요 x
export default function BoardWrite(props) {


    //0. useState 훅
    const [ category, setCategory ] = useState(''); // 입력받은 카테고리명
    const [ categoryList, setCategoryList ] = useState([]); // 서버로 부터 가져온 카테고리 리스트

//1.카테고리 목록가져오기 함수 [실행조건 : 페이지가 렌더링 되었을떄]

    function getbcategory(){
        axios
            .get("/board/bcategorylist")
            .then( res => { setCategoryList(res.data); })
            .catch( err =>{console.log(err);})
    }
    //2.
    useEffect(getbcategory,[]); //페이지가
    //카테고리 등록 함수 [카테고리 등록 버튼 눌렀을때]
    const setbcategory = () => {
        if(category == '') {alert("카테고리명을 입력후 등록해주세요"); return; }
            axios
                .post("/board/setbcategory" ,{bcname : category} )
                .then(response => {
                    if(response.data == true){alert("카테고리 등록 성공");  getbcategory();}
                    else{alert("카테고리 등록실패")}
                })
                .catch((error) => {console.log(error)})

}
    //3. 입력받은 게시물 등록함수 [ 실행조건/ 글쓰기 등록 버튼 눌렀을때
    const setboard = () =>{
        //1.카테고리 선택 유효성 검사
        if(bcno == 0){alert("카테고리를 선택해 주세여"); return; }
        //2. 로그인 여부 검사
       /* axios
            .get("/member/getloginMno")
            .then((response) =>{if(response.data== ''){alert('로그인후 게시물 작성이 가능합니다.'); return;}})*/

        let boardform = document.querySelector(".boardform")
        let formdata = new FormData(boardform);
        formdata.set("bcno" , bcno); // 펌데이터의 카테고립 ㅓㄴ호 추가
        formdata.set("bcontent" , bcontent); // 폼데이터의 내용 추가

        axios
            .post("/board/setboard" , formdata , {headers: {'Content-Type':'multipart/form-data'}})
            .then(res => {
                console.log(res.data)
                if(res.data == true){alert("게시물 작성 성공")}
            })
             .catch(err=>{console.log(err)})
    }

/*const setboard = () => {alert("게시물 추가합니다.");}*/
    return(
        <div className>
         <h1>글쓰기페이지</h1>

            <input type="text" className="bcname" value={category} onChange={(e)=>{setCategory(e.target.value)}} />
            <button type="button"onClick={setbcategory}>카테고리추가</button>
            <div className="bcategorybox">
                {
                  categoryList.map((c)=>{
                    return(
                        <button type="button"
                            key = {c.bcno}
                            onClick={()=>{bcno =c.bcno; alert(bcno); }}>
                            {c.bcname}
                         </button>
                    )
                  })
                }
            </div>

            <form className="boardform">
                제목 : <input type="text" name="btitle" />
                             <CKEditor
                                    editor={ ClassicEditor }
                                    data=""
                                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data } }
                                />
                첨부파일 : <input type="file" name="bfile" />
                <button type="button" onClick={setboard}>등록</button>
            </form>
        </div>


    );
}