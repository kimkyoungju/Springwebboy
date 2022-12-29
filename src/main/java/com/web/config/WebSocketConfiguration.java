package com.web.config;


import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.websocket.WebSocketContainer;

@Configuration // 설정 어노테이션
@EnableWebSocket // 웹소켓
public class WebSocketConfiguration implements WebSocketConfigurer {
                                    //1. web 소켓 설정 인터페이스
    @Autowired
    private  ServerSocketHandler serverSocketHandler; // 서버 소켓 클래스 호출

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler( serverSocketHandler , "/chat").setAllowedOrigins("*");
        //WebSocketHandler에 작성한 서버소켓 클래스로 설정하기 ,path : 서버소켓 엔드포인트 setAllowedOrigins
        //setAllowedOrigins(URL) : 해당 핸들럴를 요청할수 있는 url { *: 모든 url 혹은 도메인


    }
}


