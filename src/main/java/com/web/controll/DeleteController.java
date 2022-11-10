package com.web.controll;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    //p.76
    @DeleteMapping("/{variable}")
        public String delete( @PathVariable String variable) {
        return variable;
    }

    //2.p.76
    @DeleteMapping("/request1")
    public String getRequsetParam1( @RequestParam("variable") String variable) {
        return variable;
    }

}
