package com.web.controll.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1") //url 경로 정의
public class Test1Controller {

//1. text.html 열기
    /*@GetMapping("")
    public String gettext() {
        return "test1.html";
    }*/
@GetMapping("")
public Resource gettext() {
    return new ClassPathResource("templates/test1.html");
}
    //반환타입 : 문자열
    //반환타입 : Html
        //반환 타입 : Resource 반환값
        //반환값 : new ClassPathResource("html경로");
            //프로젝내 Resource  폴더부터 경로 시작
        //import org.springframework.core.io.Resource;
}
