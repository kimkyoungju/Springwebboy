import React ,{useState ,useEffect} from 'react'

import {
 useParams // 라우터 경로상의 매개변수 호출 훅 [키 : 값 , 키 : 값 쿼리스트링 형식]
 } from "react-router-dom";
import axios from 'axios'


export default function Boardview(props) {
    const params = useParams();
    const [board , setBoard] = useState({});

    useEffect (
     ()=> axios
            .get("/board/getboard",{params :{bno : params.bno }} )
            .then(res => {setBoard(res.data); console.log(res)})
    ,[])

    const [login,setLogin] = useState({}); // 로그인 정보 메모리

    useEffect( //2. 서버로부터 해당 로그인된 회원의 아이디
     ()=>axios.get("/member/getloginMno")
               .then(res => {setLogin(res.data); console.log(res)} )
    ,[])
     console.log(login)
    const onDelete= () => { //3. 서버로 부터 해당 게시물 번호를 이용한 삭제 요청
        axios.delete("/board/delboard" , {params : {bno : params.bno}})
             .then(res => {alert("삭제 성공"); window.location.href="/board/list";})
    }

    //5. 해당 게시물 번호의 해당하는 업데이트 ㅍ페이지로 이동
    const getUpdate=()=>{window.location.href='/board/update/'+params.bno}

    return(
        <div>
            <div> {board.btitle}</div>
            <div dangerouslySetInnerHTML={{__html:board.bcontent}}></div>
            {board.bfilename != '' &&<a href={"/board/filedownload?filename="+ board.bfilename}>{ board.bfilename }</a>}
            <div>
               {login == board.memail && <button type="button" onClick={onDelete}>삭제</button>}

               {login == board.memail &&  <button type="button"  onClick={getUpdate}>수정</button>}
            </div>
        </div>

    )

}