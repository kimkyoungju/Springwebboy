import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import Index from './component/Index'
import Signup from './component/member/Signup'
//1. 사용할 컴포넌트 호출 [ import 컴포넌트명 from 파일명]



import Library from './Book/chapter3/Library';
import Clock from './Book/chapter4/Clock'
import CommentList from './Book/chapter5/CommentList'
import NotificationList from './Book/chapter6/NotificationList' //6장
import Counter from './Book/chapter7/Ex1_Hook' // 7장
import Accommodate from './Book/chapter7/Accommodate' // 7장
// 2. Dom 컨테이너 [ public-> index.html 안에 있는 태그 ]
const root = ReactDOM.createRoot(document.getElementById('root'));
// * 프로젝트


    root.render(
      <React.StrictMode>
        <Index />
      </React.StrictMode>
    );


//3. dom컨테이너 렌더링

//기본값 [ library.jsx 컴포넌트를 root 에 렌더링
/*
root.render(
  <React.StrictMode>
    <Library />
  </React.StrictMode>
);
*/
//setInterval(()=>{},밀리초)
    //setInterval((인수)=>{실행문},밀리초)
/*
    setInterval(()=>{

            root.render(
              <React.StrictMode>
                <Clock />
              </React.StrictMode>
            );
    },1000);

*/

//4.
/*root.render(
  <React.StrictMode>
    <CommentList />
  </React.StrictMode>
);*/

/*
root.render(
  <React.StrictMode>
    <Signup />
  </React.StrictMode>
);
*/

//6.
/*
root.render(
  <React.StrictMode>
    <NotificationList />
  </React.StrictMode>
);*/
//7.실습
/*  root.render(
      <React.StrictMode>
        <Counter />
      </React.StrictMode>
    );*/
//7
 /* root.render(
      <React.StrictMode>
        <Accommodate />
      </React.StrictMode>
    );
*/
//3. dom 컨테이너 렌더링

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
