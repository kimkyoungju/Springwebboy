package com.web.domain.dto;

import com.web.domain.entity.nomember.NomemberEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class NomemberDto {
    private int nno;
    private String ncategory;
    private String ncontent;


    public NomemberEntity entity(){
        return NomemberEntity.builder()
                .nno(this.nno)
                .ncategory(this.ncategory)
                .ncontent( this.ncontent)
                .build();
    }



}
