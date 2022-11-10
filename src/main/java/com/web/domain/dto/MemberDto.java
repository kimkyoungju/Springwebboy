package com.web.domain.dto;


import lombok.*;

@NoArgsConstructor //빈생성자
@AllArgsConstructor // 풀생성자
@ToString //객체내 필드 정보확인
@Getter @Setter // get set
@Builder //객체 생성 안정성 보장
public class MemberDto {

    private String name;
    private String email;
    private String organization;


}
