
//1. import : 상대경로
import React from 'react';
import Notification from './Notification';


//2. data

const reservedNotifications = [
    {id : 1,message : "안녕하세요, 오늘 일정 알려드립니다." },
    {id : 2, message : "점심 입니다."},
    {id : 3, message : "퇴근하겠습니다."  }
];

//3. 타이머 변수 [ interval]
var timer

//4.클래스를 이용한 컴포넌트 만들기
class NotificationList extends React.Component{
    //1. 생성자
    constructor(props){
        super(props);
        this.state = {notifications : [ ]};
    }
    //2. 함수1[컴포넌트 생성]
    componentDidMount(){
        const{notifications} = this.state; // 생명주기
        //timer = setInterval(()=>{실행코드},1000);
        timer = setInterval(()=>{
            if(notifications.length< reservedNotifications.length){
                const index = notifications.length;
                notifications.push(reservedNotifications[index]);
                this.setState( {notifications : notifications} );
            }else{
                this.setState( {notifications :[] } ) //상태초기화
            }
        },1000); //1초마다  위코드 실행

    }
    componentWillUnmount(){
        if(timer){ // timer 값이 존재하면 true
        clearInterval(timer); // setInterval 종료
        }

    }
    //3. 함수2
        render(){
            return (
                <div>
                    {this.state.notifications.map((n)=>{
                        return<Notification id={n.id} message={n.message} />;
                    })}
                </div>
            )
        }
}
//3.
export default NotificationList;

/*리스트 변수명.map((반복변수명)=> {return 반환값})*/



