package com.web.domain.dto;


import com.web.domain.entity.member.MemberEntity;
import lombok.*;

@NoArgsConstructor //빈생성자
@AllArgsConstructor // 풀생성자
@ToString //객체내 필드 정보확인
@Getter @Setter // get set
@Builder //객체 생성 안정성 보장
public class MemberDto {

    private int mno;

    private String memail;
    private String mpassword;
    private String mphone;
    // * dto --> entity변환
    public MemberEntity toEntity() {
        return  MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();

    }



}
