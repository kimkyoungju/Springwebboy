package com.web.controll;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    //리액트 사용시 x
   /* @GetMapping("/")
    public Resource getindex(){
        return new ClassPathResource("templates/Index.html");
    }
*/
}
