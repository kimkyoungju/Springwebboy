package com.web.domain.entity.nomember;

import com.web.domain.dto.NomemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity // 엔티티 정의
@Table(name = "nomember") // 테이블명 정의
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString // 롬복

public class NomemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nno;

    @Column
    private String ncategory;


/*
    @ManyToOne
    @JoinColumn(name ="write")
    @ToString.Exclude
    private  NwriteEntity nwriteEntity;
*/



    public NomemberDto noDto() {
        return  NomemberDto.builder()
                .nno(this.nno)
                .ncategory(this.ncategory)
                .nno(this.nno)
                .build();


    }

}
