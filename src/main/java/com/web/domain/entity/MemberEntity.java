package com.web.domain.entity;


import com.web.domain.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor //빈생성자
@AllArgsConstructor // 풀생성자
@ToString //객체내 필드 정보확인
@Getter
@Setter // get set
@Builder //객체 생성 안정성 보장
@Entity //해당 연결된 DB의 테이블과 매핑[ 연경 ]
@Table(name = "member") // 테이블 명  DB 테이블 이름
public class MemberEntity {

    //1. 필드
    @Id ///엔티티당 무조건 1개이상 [pk]
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동번호 부여 auto_increment
    private int mno; //회원번호 필드
    @Column (nullable = false) // not null
    private String memail; // 회원이메일 = 회원아이디 필드
    @Column (nullable = false) //  not null
    private String mpassword; // 회원 비밀번호 필드

    @Column(nullable = false)//  notnull
    private String mphone ; //회원전화


    //2. 생성자 [롬복으로 사용]
    //3. 메소드 [롬복으로 사용]
    public MemberDto toDto() {
        return MemberDto.builder()// 빌더 시작
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();//끝
    }

}
