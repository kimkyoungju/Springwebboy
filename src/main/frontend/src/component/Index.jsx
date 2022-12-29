// jsx : 리액트 확장 표현식 파일
//컴포넌트 단위 애플리케이션 제작
//spa :싱글 페이지 애플리케이션 [ 페이지는 하나다]
       //라우터 라이브러리 : 가상URL
//컴포넌트 만들기 준비물
    //1. 첫글자는 대문자 [ 컴포넌트명 == 파일명 ]
    //2.리액트[프레임워크가 아니다 ] 라이브러리 집합소 [import 많다]
        //1.import React from 'react';
        //2. 컴포넌트 정의
        //3. export default 컴포넌트명;
                //2,3 : export default function 컴포넌트명() {return (렌더링할 코드);}

//1.
import React from 'react';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import Signup from './member/Signup';
import Login from './member/Login';
import BoardList from './board/BoardList'
import BoardWrite from './board/BoardWrite'
import Boardview from './board/Boardview'
import BoardUpdate from './board/BoardUpdate'
import Chatting from './chatting/chatting'
import Roomwrite from './room/Roomwrite'



import BookList from '../Book/BookList'
import Library from '../Book/chapter3/Library';
import Clock from '../Book/chapter4/Clock'
import CommentList from '../Book/chapter5/CommentList'
import NotificationList from '../Book/chapter6/NotificationList' //6장
import Accommodate from '../Book/chapter7/Accommodate' // 7장
import TestState from '../Book/chapter8/TestState' // 7장
import LandingPage from '../Book/chapter9/LandingPage' // 7장
import AttendanceBook from '../Book/chapter10/AttendanceBook' // 7장
import SignUp from '../Book/chapter11/SignUp' // 7장
import Calculator from '../Book/chapter12/Calculator' // 12장

/*
import Clock from './chapter4/Clock'
import CommentList from './chapter5/CommentList'
import NotificationList from './chapter6/NotificationList' //6장
import Counter from './chapter7/Ex1_Hook' // 7장
*/

//라우터 설치 [ 터미널 ] npm i react-router-dom
  //import { 컴포넌트명 } from 'react-router-dom';
//  import { BrowserRouter , Routes , Route , Link} from 'react-router-dom';
//  import { HashRouter,BrowserRouter, Routes, Route, Link,Router } from "react-router-dom"; 버전 6
  import { HashRouter,BrowserRouter, Routes, Route, Link,Router } from "react-router-dom";
    //BrowserRouter :
    //vs HashRouter :
    //Routes : Route 목록리스트
    //Route : 가상 URL 만들기 --> 해당 URL에 따른 컴포넌트 렌더링 [SPA]
    //Link : <----> a태그 : 하이퍼 링크
        //link to = "Route URL"
    //vs Router :
//2.
export default function Index(props){
    return(
        <div className="webbox">
                <BrowserRouter>
                         <Header/>
                                <Routes>
                                       <Route path="/" element={<Home />}/>
                                       <Route path="/member/signup" element={ <Signup/> }/>
                                       <Route path="/member/login"  element={ <Login/> }/>
                                       <Route path="/board/list"  element={ <BoardList/> }/>
                                       <Route path="/board/write"  element={ <BoardWrite/> }/>
                                       <Route path="/board/view/:bno"  element={ <Boardview/> }/>
                                        <Route path="/board/update/:bno"  element={ <BoardUpdate/> }/>
                                        <Route path="/chatting"  element={ <Chatting/> }/>
                                        <Route path="/room/write"  element={ <Roomwrite/> }/>

                                       <Route path="/book/booklist"  element={ <BookList/> }/>
                                       <Route path="/chapter3/library"  element={ <Library/> }/>
                                       <Route path="/chapter4/clock"  element={ <Clock/> }/>
                                       <Route path="/chapter5/commentList"  element={ <CommentList/> }/>
                                       <Route path="/chapter6/notificationList" element={<NotificationList/>} />
                                       <Route path="/chapter7/accommodate"  element={ <Accommodate/> }/>
                                       <Route path="/chapter8/testState"  element={ <TestState/> }/>
                                       <Route path="/chapter9/landingPage"  element={ <LandingPage/> }/>
                                       <Route path="/chapter10/AttendanceBook"  element={ <AttendanceBook/> }/>
                                       <Route path="/chapter11/SignUp"  element={ <SignUp/> }/>
                                       <Route path="/chapter12/Calculator"  element={ <Calculator/> }/>

                                </Routes>
                         <Footer/>
                </BrowserRouter>
            </div>
    );
}

//3.
