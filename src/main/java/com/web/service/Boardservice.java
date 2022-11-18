package com.web.service;

import com.web.domain.dto.BoardDto;
import com.web.domain.entity.board.BoardEntity;
import com.web.domain.entity.board.BoardRepository;
import com.web.domain.entity.member.MemberEntity;
import com.web.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Boardservice {
    // ------------1.전역변수---------------//
    @Autowired
    private BoardRepository boardRepository; // 게시물 리포지토리 객체 선언
    @Autowired
    private MemberRepository memberRepository; // 회원리포지토리 객체 선언
    @Autowired
    private HttpServletRequest request;

    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    // 1. 메소드
            /*
                1. insert : boardRepository.save( 삽입할엔티티 )            BoardEntity entity
                2. select : boardRepository.findAll()                List<BoardEntity> elist
                3. select : boardRepository.findById( pk번호 )        Optional<BoardEntity> optional
                4. delete : boardRepository.delete( 삭제할엔티티 )
             */
    // ------------ 2. 서비스 ------------- //
    // 1. 게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto ){

        //1. 로그인 정보확인 [ 세션 =loginMno]
        Object object = request.getSession().getAttribute("loginMno");
        if(object == null){return  false;}
       //2.로그인된 회원정보 호출
        int mno =  (Integer)object;
        //3. 회원번호 ---> 회원정보 호출
        Optional<MemberEntity>optional = memberRepository.findById(mno);
        if (!optional.isPresent()){return false;}
        //4.로그인된 회원의 엔티티
        MemberEntity memberEntity = optional.get();



        BoardEntity boardEntity  = boardRepository.save( boardDto.toEntity() );  // 1. dto --> entity [ INSERT ] 저장된 entity 반환
        if( boardEntity.getBno() != 0 ) {       // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공
            //fk 대입
            boardEntity.setMemberEntity(memberEntity);
            //양방향 [pk필드에 fk연결]
            memberEntity.getBoardEntityList().add(boardEntity);
            return true;
        }
        else{ return false; } // 2. 0 이면 entity 생성 실패
    }
    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        List<BoardEntity> elist =  boardRepository.findAll(); // 1. 모든 엔티티 호출한다.
        List<BoardDto> dlist = new ArrayList<>(); // 2. 컨트롤에게 전달할때 형변환[ entity->dto ] : 역할이 달라서
        for( BoardEntity entity : elist ){ // 3. 변환
            dlist.add( entity.toDto() );
        }
        return dlist;  // 4. 변환된 리스트 dist 반환
    }
    // 3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard( int bno ){
        Optional< BoardEntity > optional = boardRepository.findById(bno); // 1. 입력받은 게시물번호로 엔티티 검색 [ Optional]
        if( optional.isPresent() ){// 2. Optional 안에 있는 내용물 확인 .isPresent()
            BoardEntity boardEntity = optional.get(); // 3. 엔티티 꺼내기 .get();
            return boardEntity.toDto(); // 4.형변환 반환
        }else{
            return null; // 4. 없으면 null
        }
    }
    // 4. 게시물 삭제
    @Transactional
    public boolean delboard( int bno ){
        Optional<BoardEntity> optional = boardRepository.findById( bno);
        if( optional.isPresent() ){
            BoardEntity entity =  optional.get();
            boardRepository.delete( entity ); // 찾은 엔티티를 삭제한다.
            return true;
        }else{ return false; }
    }
    // 5. 게시물 수정 [ 첨부파일 ]
    @Transactional
    public boolean upboard( BoardDto boardDto){
        // 1. DTO에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById( boardDto.getBno() );
        if( optional.isPresent() ){  // 2.
            BoardEntity entity = optional.get();
            // * 수정처리 [ 메소드 별도 존재x /  엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            entity.setBtitle( boardDto.getBtitle() );
            entity.setBcontent( boardDto.getBcontent()) ;
            entity.setBfile( boardDto.getBfile() );
            return true;
        }else{  return false;  }
    }
}