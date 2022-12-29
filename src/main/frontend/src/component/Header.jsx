
import React,{useState} from 'react'; //컴포넌트 호출
import Styles from '../css/header.css'; //src -> css-> header.css
import logo from '../img/img.png'
import { HashRouter,BrowserRouter, Routes, Route, Link,Router } from "react-router-dom";
import axios from 'axios';

//이미지 적용
//2.
export default function Header() {
      const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링

         axios
             .get("/member/getloginMno")
             .then( (response) => { setLogin( response.data ); } ).
             catch( (error) => {alert(error)})
    /*//1. 서버와 통신 [axios]
    axios.get('http://localhost:8080/member/getloginMno').then(res => {alert('서버와 통신됨')})
    *///axios.get('URL').then(response => {응답})

     return(
     <div>
         <div className="header">
                <div className="header_logo">
                    <Link to="/"> <img className="logo" src={logo}/></Link>
                </div>
                <ul className="top_menu">
                    {login == "" ?
                        (
                            <>
                                 <li> <Link to="/member/login" >로그인</Link></li>
                                 <li> <Link to="/member/signup" >회원가입</Link></li>
                            </>
                        )
                    :
                      (
                            <>
                                <li> { login } </li>
                                <li> <a href="/member/logout">로그아웃</a></li>
                            </>
                      )
                    }
                    <li> <a href="/book/booklist">리액트빨간펜</a></li>
                    <li> <a href="/board/list" >자유게시판</a></li>
                     <li> <a href="/room/write" >방등록</a></li>
                    <li> <Link to="/chatting" >익명채팅방</Link></li>

                </ul>
         </div>
     </div>
     );
}