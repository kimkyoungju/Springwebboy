package com.web.controll.test;

import com.web.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //@ResponseBody 생략가능  -> ResponseBody
@RequestMapping("/api/v1/put-api")
public class PutController {
    //1.p.70


    @PutMapping("/member")
    public String putMember(@RequestBody Map<String , String> putdata) {
        return putdata.toString();
    }

    //2.p.71
    @PutMapping("/member1")
    public String putMemberDto(@RequestBody MemberDto memberDto) {

    return memberDto.toString();
    }

    @PutMapping("/member2")
    @ResponseBody  //contentType : "application/json",
    public MemberDto putMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

}
