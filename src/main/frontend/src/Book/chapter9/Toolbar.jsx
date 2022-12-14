//p279
import React from 'react'
import Styles from './Toolbar.css'
export default function Toolbar(props) {
    const {isLoggedIn,onClickLogout ,onClickLogin} = props;

    return(
        <div className="wrapper">
            {props.isLoggedIn &&<span className="greeting">환영합니다</span>}
            {/*isLoggedIn ? (참DOM) : (거짓DOM)*/}
             {isLoggedIn ?(
                <button onClick={onClickLogout}>로그아웃</button>
             ):(
                <button onClick={onClickLogin}>로그인</button>
             )}
        </div>
    )
}