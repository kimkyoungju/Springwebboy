import React,{useState} from 'react';
import { HashRouter,BrowserRouter, Routes, Route, Link,Router } from "react-router-dom";

import Library from './chapter3/Library';
import Clock from './chapter4/Clock'
import CommentList from './chapter5/CommentList'
import NotificationList from './chapter6/NotificationList' //6장
import Accommodate from './chapter7/Accommodate' // 7장
import TestState from './chapter8/TestState' // 7장
import LandingPage from './chapter9/LandingPage' // 7장
import AttendanceBook from './chapter10/AttendanceBook'
import Signup from './chapter11/SignUp'
import Calculator from './chapter12/Calculator'

export default function BookList(props){
/*

    const [onClickstate,onClickNostate] = useState(false);

    const onClickstate = () =>{onClickstate(true);};

    const onClickNostate = () =>{onClickstate(false);};
*/



    return(
        <div>
            <ul>
              <li>  <a href ="/chapter3/library" >챕터3</a></li>
               <li>   <a href ="/chapter4/Clock" >챕터4</a></li>
               <li>   <Link to="/chapter5/CommentList" >챕터5</Link></li>
               <li>   <a href="/chapter6/NotificationList" >챕터6</a></li>
               <li>   <Link to="/chapter7/Accommodate" >챕터7</Link></li>
               <li>  <Link to="/chapter8/TestState" >챕터8</Link></li>
               <li>   <Link to="/chapter9/LandingPage" >챕터9</Link></li>
               <li>    <Link to="/chapter10/AttendanceBook" >챕터10</Link></li>
               <li>    <Link to="/chapter11/SignUp" >챕터11</Link></li>
               <li>     <Link to="/chapter12/Calculator" >챕터12</Link></li>
            </ul>
        </div>
    );
}



