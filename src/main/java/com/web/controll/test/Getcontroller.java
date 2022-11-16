package com.web.controll.test;

import com.web.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


    @RestController
    @RequestMapping("/api/v1/get-api") //요청 URL 정의하기
    public class Getcontroller {
    //p.57
    @RequestMapping(value = "/hello" , method = RequestMethod.GET)//get요청
    public String  getHello(){  //함수명 [ 아무거나 / 중복 x]
        return "해당메소드로 접근"; //response 응답
    }
    //1.GetMapping
    //2.postMapping
    //3.PutMapping
    //4.DeleteMapping


    //p.58
    @GetMapping("/name")
    public String Getname(){
        return "Flature";
    }
    //p.59
    @GetMapping("/variable1/{variable}")
    public String getvariable1(@PathVariable String variable){
        return variable;
    }
    //4. 60
    @GetMapping("/variable2/{variable}")
    public String getvariable2(@PathVariable(value = "variable") String test){
        return test;
    }
    //4-2
    @GetMapping("/variable3")
    public String getvariable3(@RequestParam String variable){
        return variable;
    }
    //p.61
    @GetMapping("/requst1")
    public String getrequstparam(@RequestParam String name ,
                             @RequestParam String email,
                             @RequestParam String organization){
        return name +" " + email + " " + organization;
    }
    //p.62
    @GetMapping("/requst2")
    public String  getrequstparam1(@RequestParam Map<String , String>params){


        return params.toString();
    }
    //p66
    @GetMapping("/requst3")
    public String getrequstparam3(MemberDto memberDto){
        return memberDto.toString();
    }
    }
