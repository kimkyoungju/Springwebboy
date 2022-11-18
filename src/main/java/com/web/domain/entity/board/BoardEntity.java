package com.web.domain.entity.board;

import com.web.domain.dto.BoardDto;
import com.web.domain.entity.BaseEntity;
import com.web.domain.entity.bcategory.BcategoryEntity;
import com.web.domain.entity.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity // 엔티티 정의
@Table(name = "board") // 테이블명 정의
@AllArgsConstructor@NoArgsConstructor@Getter
@Setter
@Builder
@ToString // 롬복
public class BoardEntity extends BaseEntity {


    @Id // pk
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto
    private int bno;            // 게시물번호
    @Column( nullable = false )     // not null
    private String btitle;      // 게시물제목
    @Column( nullable = false , columnDefinition = "TEXT")     // not null , DB 자료형사용시 columnDefinition = "DB자료형"
    private String bcontent;    // 게시물 내용
    @Column( nullable = false )     // not null
    @ColumnDefault( "0" )           // JPA insert 할 경우 default
    private int bview;          // 조회수
    @Column( nullable = false )     // not null
    private String bfile;       // 첨부파일
      // 카테고리[ 카테고리-fk ]
    // 작성일,수정일 -> 상속( 여러 엔티티해서 사용되는 필드라서 )


    //연관관계
    @ManyToOne //FK에 연결
    @JoinColumn(name="mno") // 테이블에서 사용할 fk의 필드명 정의
    @ToString.Exclude // 해당 필드는 ToString 사용하지 않는다 . 무한루프에 빠진다[양방향일 경우]
    private MemberEntity memberEntity;        //pk에 엔티티 객체// 테이�

    //연관관계 2[카테고리 번호 [PK]<--양방향--> 게시물 번호 [FK]
    @ManyToOne //FK에 연결
    @JoinColumn(name="bcno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    // 1.형변환
    public BoardDto toDto(){
        // * 빌더 패턴을 이용한 객체생성 [ *생성자 비교 ]
        return BoardDto.builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bview( this.bview )
                .bfile( this.bfile )

                .memail( this.memberEntity.getMemail().split("@")[0])
                .build();
    }
}
