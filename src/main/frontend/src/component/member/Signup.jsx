
import React from 'react';
import styles from './signup.css'
import axios from 'axios' // npm install axios 설치 했을경우만

function Signup(props) {
    //class -> className 변경
    //태그닫기
    //onclick -> onClick
    //함수호출={} jsx표현식

    //1.setmember 이벤트 함수 정의 [ 화살표 함수 정의]
    const setmember =()=>{
         let info = {
               memail : document.querySelector('.memail').value ,
               mpassword : document.querySelector('.mpassword').value ,
               mphone : document.querySelector('.mphone').value
           }
           //axios 비동기 통신함수 [ ajax ]
            //axios.MethodType("통신URL" ,전송할 data )

           axios
               .post("http://localhost:8080/member/setmember" , info )
               .then(res=>{
                let result = res.data;
                alert(result)
                if(result != 0 ){ //회원가입 성공
                    alert("회원가입 성공")
                }else{ // 실패
                    alert("회원가입 실패")
                }
           })
           .catch(err=>{console.log(err)})

        //통신 [ajax vs fetch[react내장] vs axios[react별도설치]]
        //axios 설치방법
              //npm : 라이브러리 빌드/관리[node.js]
              //1. [현재 프로젝트내]npm install axios
    }
    //2.인증코드 요청 함수
     const getauth =()=>{
            alert('클릭이벤트')
        }
    //3.타이머 함수
     const settimer =()=>{
            alert('클릭이벤트')
        }
    //4.인증버트확인 함수
    const authcode =()=>{
        alert('클릭이벤트')
    }
       return (
        <div>
        <h3> 회원가입 </h3>

        <div>
                이메일 : <input type="text" className="memail" />
                <button type="button" onClick={getauth} className="getauthbtn">인증코드받기</button><br/>
                <div className="authbox">
                    <input type="text" className="authinput" />
                    <button type="button" className="authbtn" onClick={authcode}>인증</button>
                    <span className="timerbox"></span>
                </div>
            </div>
                핸드폰 : <input type="text" className="mphone" /><br/>
                비밀번호 : <input type="text" className="mpassword" /><br/>
            <button type="button" onClick={setmember}>가입하기</button>
        </div>

       );

}

export default Signup;