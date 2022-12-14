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
export default function BookList(props){
/*

    const [onClickstate,onClickNostate] = useState(false);

    const onClickstate = () =>{onClickstate(true);};

    const onClickNostate = () =>{onClickstate(false);};
*/



    return(
        <div>
                <a href ="/chapter3/library" >챕터3</a>
                  <a href ="/chapter4/Clock" >챕터4</a>
                  <Link to="/chapter5/CommentList" >챕터5</Link>
                  <a href="/chapter6/NotificationList" >챕터6</a>
                  <Link to="/chapter7/Accommodate" >챕터7</Link>
                 <Link to="/chapter8/TestState" >챕터8</Link>
                  <Link to="/chapter9/LandingPage" >챕터9</Link>
                   <Link to="/chapter10/AttendanceBook" >챕터10</Link>
                   <Link to="/chapter11/SignUp" >챕터11</Link>

        </div>
    );
}



