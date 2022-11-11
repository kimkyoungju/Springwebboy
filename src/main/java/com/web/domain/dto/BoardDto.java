package com.web.domain.dto;

import lombok.*;

//롬복 : 생성자 ,Get/Set , ToString, 빌더 패턴
@NoArgsConstructor  @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {

    private String btitle;
    private String bcontent;

}
