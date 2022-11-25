
//1. react 임포트
import React from 'react';
//2. 컴포넌트만들기 [ 함수 만들기 ]

function Clock(props){//props : 속성 [매개변수]
        //{변수} : jsx 표현식 [변수,컴포넌트[함수],객체]등
    return(
        <div>
                <h1>안녕, 김경주</h1>
                <h2>현재시간 : {new Date().toLocaleTimeString()}</h2>
        </div>

    );

}

//3.컴포넌트 내보내기
export default Clock;








