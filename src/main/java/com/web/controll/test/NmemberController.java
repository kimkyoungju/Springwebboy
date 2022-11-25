package com.web.controll.test;

import com.web.domain.dto.NomemberDto;
import com.web.domain.dto.NwriteDto;
import com.web.service.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Nmember")
public class NmemberController {
    @Autowired
    private Boardservice boardService= new Boardservice();

    @GetMapping("/nomember")
    public Resource nomember(){ return new ClassPathResource("templates/board/nomember.html");}

    @PostMapping("/ncategory")
    public boolean nomember(@RequestBody NomemberDto ndto){
        return boardService.nomember(ndto);
    }
    @PostMapping("/nwrite")
    public boolean nwrite( NwriteDto dto){
        return boardService.nwrite(dto);
    }

    @GetMapping("/clist")
    public List<NomemberDto> clist(){ return boardService.clist();}




}
