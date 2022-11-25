import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
//1. 사용할 컴포넌트 호출 [ import 컴포넌트명 from 파일명]
import Library from './chapter3/Library';
import Clock from './chapter4/Clock'


const root = ReactDOM.createRoot(document.getElementById('root'));

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
    setInterval(()=>{

            root.render(
              <React.StrictMode>
                <Clock />
              </React.StrictMode>
            );
    },1000);




//3. dom 컨테이너 렌더링

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
