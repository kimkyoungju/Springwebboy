package com.web.domain.dto;


import com.web.domain.entity.Room.RoomEntity;
import com.web.domain.entity.Room.RoomImgEntity;
import com.web.domain.entity.Room.RoomImgRepository;
import com.web.service.RoomService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString @Builder
public class RoomDto {

    @Autowired
    private RoomImgRepository roomImgRepository;

    private  int rno;
    private String rtitle;
    private int rprice;
    private String rtrans;
    private List<MultipartFile> rimg;

    private String rname;
    private String rlat;
    private String rlng;

    private String memail;
    private  List<String>getrimg;
    public RoomEntity toEntity() {

        return RoomEntity.builder()
                .rtitle(this.rtitle)
                .rprice(this.rprice)
                .rtrans(this.rtrans)
                .rname(this.rname)
                .rlat(this.rlat)
                .rlng(this.rlng)
                .build();
    }


}