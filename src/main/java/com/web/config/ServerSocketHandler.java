package com.web.config;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component // 스프링 빈에 해당 클래스를 등록
public class ServerSocketHandler extends TextWebSocketHandler {  //서버소켓


    //0. 접속자 명단 리스트
    private  static List<WebSocketSession>list = new Vector<>();

    //1.접속

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("접속 :" +session);
        list.add(session); // 리스트 명단에 접속된 세션 추가
    }

    //2. 종료
    public void afterConnectionEstablishedClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("퇴장 :" +session);
        list.remove(session); // 리스트 명단에서 퇴장한 세션 제거
    }
    //3. 메시지 받았을때

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      for(WebSocketSession s : list){ //접속자 명단 반복문으로 출력
            s.sendMessage(message);   // 서버가 받은 메시지를 모든 접속자에게 전달
      }
    }
}
