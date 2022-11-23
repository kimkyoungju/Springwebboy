package com.web.domain.dto;

import com.web.domain.entity.nomember.NwriteEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NwriteDto {

   private   int wno;

   private String ncontent;
   private String ntitle;
   private int nno;
   private MultipartFile cfile;
   public NwriteEntity toEntity(){
       return NwriteEntity.builder()
               .wno(this.wno)
               .ntitle(this.ntitle)
               .ncontent(this.ncontent)

               .build();
   }


}
