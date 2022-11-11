package com.web.controll;

import com.web.domain.dto.BoardDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/board") //스프링 MVC 관리 mapping 들의 공통 URL
public class BoardController {

    //1. 게시물 목록 페이지 열기
    @GetMapping("/list")
    public Resource List() {
        return new ClassPathResource("temlpates/board/list.html");
    }
    //2.게시물 쓰기 페이지 열기
    @GetMapping("/write")
    public Resource write() {
        return new ClassPathResource("temlpates/board/write.html");

    }
    //-----------------------------------------
    //1.게시물 쓰기 처리
    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BoardDto boardDto) {
        System.out.println(boardDto.toString());
        //1.DTO 내용확인
        //2. -----> 서비스 [ 비지니스 로직] 로 이동
        //2. 게시물 목록 보기
    return true;
    }
    @GetMapping("/getboards")
    public ArrayList<BoardDto>getboards() {
        //1. -----> 서비스 [비지니스 로직] 로 이동
        //2. 반환
        return null;


    }

   /* //3. 게시물 개별조회
    @GetMapping("/getboard")
    //4.게시물 수정처리
    @PutMapping("/updateboard")

    //5. 게시물 삭제 처리
    @DeleteMapping("/deleteboard")

*/
}
