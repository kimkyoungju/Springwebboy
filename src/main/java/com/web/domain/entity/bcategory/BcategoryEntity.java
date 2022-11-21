package com.web.domain.entity.bcategory;


import com.web.domain.dto.BcategoryDto;
import com.web.domain.entity.BaseEntity;
import com.web.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table(name="bcategory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString // 롬복
public class BcategoryEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int bcno;
  private String bcname;

  @OneToMany(mappedBy = "bcategoryEntity")
  @Builder.Default
  private List<BoardEntity> boardEntityList
          = new ArrayList<>();

  public BcategoryDto getDto(){

    return BcategoryDto.builder().bcno(this.bcno).bcname(this.bcname).build();



  }
}
