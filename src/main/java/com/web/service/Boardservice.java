package com.web.service;

import com.web.domain.dao.BoardDao;
import com.web.domain.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service // 컴포넌트 [ Springmvb]
public class Boardservice {


    //1. 게시물 등록 서비스
    public  boolean setboard(BoardDto boardDto){
        return new BoardDao().setboard(boardDto);
    }
    //2. 게시물 목록 서비스
    public ArrayList<BoardDto>getboards(){

        return  new BoardDao().getboards();
    }


}