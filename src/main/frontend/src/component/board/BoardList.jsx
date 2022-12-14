import React ,{useState , useEffect} from 'react'
import axios from 'axios'
import Pagination from "react-js-pagination"; // react-js-pageination 컴포넌트 가져오기

export default function List(props){
    const [ pageInfo , setpageInfo] = useState({bcno : 0, page : 1, key : "" , keyword : ""})   //1. 요청 정보 객체  state
    const [ pageDto , setpageDto] = useState({list : []})   //1. 게시물 리스트 state
    const [ bcategory , setBcategoryList] = useState([])   //1. 게시물 리스트 state
                                        //[ ] : array/list  { } : object /dto

    //1.server : pageInfo 요청 => pageDto 응답 [실행조건 : 페이지가 렌더링 되었을떄]
    function getboardlist ( ){
        axios
            .post("/board/boardlist" , pageInfo  )
            .then(res => { console.log(res.data);setpageDto(res.data); })
            .catch(err=>{console.log(err);})
    }
    //3. 렌더링 될때 그리고 pageInfo변경 될때 마다
    useEffect(getboardlist ,[pageInfo])
    //4.
    function getBcategory(){ //4.실행조건 :mount 모든 카테고리 가져오기
        axios
                   .get("/board/bcategorylist")
                   .then( res => { setBcategoryList(res.data); })
                   .catch( err =>{console.log(err);})
    }
    useEffect(getBcategory, []) //3. [실행조건 : mount]

    const onCategory = (bcno) =>{setpageInfo({bcno : bcno, page : 1, key : "" , keyword : ""})}
    const onPage = (page) =>{setpageInfo(
                {bcno : pageInfo.bcno ,
                        page : page,
                        key : pageInfo.key ,
                        keyword : pageInfo.keyword}
                     )
                }
                                                //기존 카테고리

      /*검색*/
    const onSearch = () => {
        setpageInfo(
            {bcno : pageInfo.bcno , //카테고리 번호 [기존 그대로 ]
                    page : 1,       // 검색시 첫페이지부터 보여주기 [ 1 ]
                    key : document.querySelector('.key').value ,
                    keyword : document.querySelector('.keyword').value
                }
          )
    }
    const loadView = (bno)=>{
        window.location.href= '/board/view/'+bno

    }

    return(
    <div>
         <a href="/board/write">글쓰기[로그인했을때만표시]</a>
            <h1> 글 목록 페이지 </h1>

            <div className="bcategorybox">

                <button type="button" onClick={()=>onCategory(0)}>전체보기</button>
                {
                    bcategory.map((c) =>{
                        return(
                            <button type="button" onClick={()=>onCategory(c.bcno)}>{c.bcname}</button>
                         )
                    })
                  }
            </div>
            <table className="btable">
                {
                   pageDto.list.map((b)=>{
                        return(
                            <tr>
                                <td>{b.bno}</td>
                                <td onClick={()=>loadView(b.bno)}>{b.btitle}</td>
                                <td>{b.memail}</td>
                                <td>{b.bdate}</td>
                                <td>{b.bview}</td>
                            </tr>
                        )
                    })
                }
            </table>

            <Pagination
               activePage={pageInfo.page}
               itemsCountperPage = {3}
               totalItemsCount={pageDto.totalBoards}
               pageRangeDisplayed = {5 }
               onChange={onPage}
            />

           <div className="searchBox">
                <select className="key">
                    <option value="btitle">제목</option>
                    <option value="bcontent">내용</option>
                </select>
                <input type="text" className="keyword"/>
                <button type="button" onClick={onSearch}>검색</button>
            </div>
        </div>
    );
}