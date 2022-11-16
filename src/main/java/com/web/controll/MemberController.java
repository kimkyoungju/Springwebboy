package com.web.controll;

import com.web.domain.dto.MemberDto;
import com.web.service.MemberService;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member") // 공통 URL 매핑 주소
public class MemberController {

//1.해당 ㅋ틀래스의 메소드 사용을 위한 메모리 생성
//---------------전역객체-----------------//
    @Autowired // 제어 역변 스프링 컨테이너 빈 생성[ 외부에 메모리 위임 ]
    private MemberService memberService;






//---------------html반환 매핑-----------------//

    @GetMapping("/signup")
    public Resource getsignup(){
        return  new ClassPathResource("templates/member/signup.html"); // 프로젝트내 resource -> templates-> member->signup.html
    }

    @GetMapping("/login")
    public Resource getlogin(){

        return  new ClassPathResource("templates/member/login.html"); // �

    }
    @GetMapping("/findpassword")
    public Resource findpassword(){
        return  new ClassPathResource("templates/member/findpassword.html"); // �

    }
    @GetMapping("/delete")
    public Resource deletemember(){
        return  new ClassPathResource("templates/member/deletemember.html");
    }

   @GetMapping("/update")
   public Resource getupdate(){
        return  new ClassPathResource("templates/member/update.html"); }






    //---------------서비스/기능 매핑-----------------//

    @PostMapping("/setmember")  // 회원가입
    public int setmember(@RequestBody MemberDto memberDto) {
        //1. 서비스 [ 비즈니스 로직 ] 호출
        //0. 다른 클래스 메소드 호출하는 방법
        //1. new , 2. 싱글톤 3.생성자().
        // = 메모리 존재 JVM VS 스프링 컨테이너
        int result = memberService.setmember(memberDto);
        //2. 반환
        return result;
    }
     @PostMapping("/getmember")
            public int getmember(@RequestBody MemberDto memberDto){

        int result = memberService.getmember(memberDto);
            return result;
        }

     @GetMapping("/getpassword")
    public String getpassword(@RequestParam("memail") String memail){
         String result = memberService.getpassword(memail);
        return result;


     }
     @DeleteMapping("/setdelete") // 회원탈퇴
    public int setdelete(@RequestParam("mpassword") String mpassword){
        //1. 서비스처리
         int result = memberService.setdelete(mpassword);

         // 2. 서비스 결과 반환

        return result;

     }


     @PutMapping("/setupdate") // 회원수정
    public int setupdate(@RequestParam("mpassword") String mpassword){
        int result = memberService.setupdate(mpassword);
        return result;
     }
    @GetMapping("/getloginMno") //로그인 정보 확인
    public int getloginMno(){
        int result = memberService.getloginMno();
        return result;
    }

    @GetMapping("/logout") // 로그아웃
    public int logout(){
        int result = memberService.logout();
        return result;
    }

    @GetMapping("/list") // 회원목록 �
    @ResponseBody
    public List<MemberDto> list(){
        List<MemberDto> list = memberService.list();
        System.out.println("확인" + list);
        return list;
    }


}
