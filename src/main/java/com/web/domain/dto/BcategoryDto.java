package com.web.domain.dto;


import com.web.domain.entity.bcategory.BcategoryEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString // 롬복
public class BcategoryDto {


    private int bcno;
    private String bcname;

    public BcategoryEntity toEntity(){

        return BcategoryEntity.builder()
               .bcno(this.bcno)
                .bcname(this.bcname)
                .build();
    }
}
