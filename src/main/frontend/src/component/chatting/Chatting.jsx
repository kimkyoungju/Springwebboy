import React ,{useState ,useEffect , useRef } from 'react';

export default function Chatting(props){

    //서버소켓과 연결여부 저장하는 메모리
    const [ socketConn , setSockConn] = useState(false);
    /*
    //서버소켓 url
    const webSocketUrl = 'ws://localhost:8080/chat';
   */
   const [msgList , setMsgList] = useState([]);
   //2클라이언트 소켓
   let ws =useRef( null);

   // 컴포넌트가 mount 될때 서버 소켓 연결 , unmount 될때 서버소켓 닫기
   //useEffect(()=>{},[]); useEffect(함수명=>{},[]);
   useEffect(()=>{
        if( !ws.current){
            //클라이언트 소켓 생성
            ws.current = new WebSocket('ws://localhost:8080/chat'); //클라이언트 소켓 생성 [ 서버소켓 주소
            //1. 클라이언트소켓이open
            ws.current.onopen = () => {
                setSockConn(true);
                }
            //2. 클라이언트소켓이close
            ws.current.onClose = (e) => {
                console.log("닫기" +e)
                }

            //3. 클라이언트소켓이error
            ws.current.onerror= (e) => {
                console.log("에러"+e);
                }

            //4. 서버소켓으로부터 message 받았을때
             ws.current.onmessage = (e) => {
               let data = JSON.parse(e.data);
               setMsgList((prevMsgList)=>[...prevMsgList,data]); // 기조상태 + 새로운 값 => 상태 업데이트
                }
        }
   },[]);
    // 전송버튼 클릭했을때 이벤트 send 이벤트
    const onMsg = () =>{
        let msg = document.querySelector(".msgInput").value;
        ws.current.send(JSON.stringify( {massage : msg} ) );
        document.querySelector(".msgInput").value = ''

    }

    return (
        <div>
            접속상태 : <span></span>
            <div>
                채팅입력 : <input type="text" className="msgInput" />
                <button type="button" onClick={onMsg}>전송</button>
            </div>

            <div>
                <h6>채팅창</h6>
                {
                    msgList.map((msg)=>{
                        return <div> {JSON.stringify(msg)}</div>
                    })
                }
            </div>
        </div>
    )
}


/*
1.setState() : 상태 없데이트
2.prevState : 이전상태 호출

상태리스트에 새로운 값 추가
setMsgList((prevMsgList)=>{}

    리스트.forEach(()=>{}) : 반환[return]없음
    리스트.map(()=>{}) : 반환이 있다
*/


