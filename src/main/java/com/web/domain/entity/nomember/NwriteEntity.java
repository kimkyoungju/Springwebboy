package com.web.domain.entity.nomember;


import com.web.domain.dto.NwriteDto;
import com.web.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;

@Entity // 엔티티 정의
@Table(name = "nwrite") // 테이블명 정의
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString // 롬복

public class NwriteEntity extends BoardEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   int wno;

    @Column
    private String ntitle;
    @Column
    private String ncontent;

    @Column
    private String cfile;

    @ManyToOne
    @JoinColumn(name ="nno")
    @ToString.Exclude
    private NomemberEntity nomemberEntity;



    public NwriteDto dto(){
        return NwriteDto.builder()
                .wno(this.wno)
                .ntitle(this.ntitle)
                .ncontent(this.ncontent)
                .cfilename(this.cfile)
                .build();

    }


}
