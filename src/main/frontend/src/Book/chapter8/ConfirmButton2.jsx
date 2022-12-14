//p.261
import React,{useState} from 'react'

function ConfirmButton2(props) {
    //1.useState 훅을 이용한 state사용
    const[isConfirmed , setIsConfirmed] = useState(false);
    //2. 이벤트 함수 변수
    const handleConfirm = () =>{
        setIsConfirmed((preIsConfirmed)=>!preIsConfirmed);
    }
    const handleConfirm2 = () =>{
        if(isConfirmed){setIsConfirmed(false)}
        else{setIsConfirmed(true)}
    }


      return (
      <div>
             <button onClick={handleConfirm}
             disabled ={isConfirmed}>
                 {isConfirmed ? "확인됨" : "확인하기"}
             </button>

             <button onClick={handleConfirm2}>
             버튼
             </button>
             {isConfirmed && <input type="text" />}
      </div>

         );

}
export default ConfirmButton2;

