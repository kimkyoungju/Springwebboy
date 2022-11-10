package com.web.controll;

import com.web.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class Postcontroller {


    //1.p.68
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample() {
        return "hello Post API";
    }
    //2.p.69
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, String>postData) {
        return postData.toString();
    }
    //p.69
    @PostMapping("/member2")
    public String postMemberDto(@RequestBody MemberDto  memberDto) {
      return memberDto.toString();
    }


}
