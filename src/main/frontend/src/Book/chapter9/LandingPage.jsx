//p.281 : 컴포넌트
import React,{useState} from 'react';
import Toolbar from './Toolbar';

export default function LandingPage(props) {
    //1. state 선언
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    //2. 함수
    const onClickLogin = () =>{setIsLoggedIn(true);}; //로그인 실행
    //3.함수
    const onClickLogout = () =>{setIsLoggedIn(false);};

    //4.렌더링
    return(
        <div>
            <Toolbar
                isLoggedIn={isLoggedIn}
                 onClickLogin={onClickLogin}
                onClickLogout ={onClickLogout}
              />
              <div style={{padding:16}}>소플과 함께하는 리액트 공부 !</div>
        </div>

    )
}