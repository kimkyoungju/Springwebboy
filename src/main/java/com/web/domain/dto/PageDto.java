package com.web.domain.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PageDto {

    private int bcno;
    private int page;
    private  String key;
    private String keyword;

    //결과 [ 게시물 ] 리스트
    @Builder.Default
    private List<BoardDto> list = new ArrayList<BoardDto>();
    private  int startbtn;       //페이징 버튼 시작번호
    private  int endbtn;         // 페이징 버튼 끝번호
    private  Long totalBoards;   // 총 게시물 수
    // 게시물 전체개수 등등

}
