package com.web.domain.dto;

import com.web.domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

//롬복 : 생성자 ,Get/Set , ToString, 빌더 패턴
@NoArgsConstructor @AllArgsConstructor@Getter @Setter @ToString @Builder
public class BoardDto {
    private int bno;            // 게시물번호
    private String btitle;      // 게시물제목
    private String bcontent;    // 게시물 내용
    private int bview;          // 조회수
    private int bcno;
    private MultipartFile bfile;       // 첨부파일

    private String memail;     // 회원 아이디
    //1. 형변환
    public BoardEntity toEntity(){
        // * 생성자를 이용한 객체 생성 [ *빌더패턴 비교 ]
        return  BoardEntity.builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bview( this.bview )
               // .bfile( this.bfile )
                .build();
    }
}

