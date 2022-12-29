package com.web.service;


import com.web.domain.dto.RoomDto;
import com.web.domain.entity.Room.RoomEntity;
import com.web.domain.entity.Room.RoomImgEntity;
import com.web.domain.entity.Room.RoomImgRepository;
import com.web.domain.entity.Room.RoomRepository;
import com.web.domain.entity.member.MemberEntity;
import com.web.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomImgRepository roomImgRepository;

    //String path = "C:\\Users\\504\\Desktop\\springweb\\Springwebboy\\build\\resources\\main\\static\\static\\bupload";
    // 현재 스프링 의 배포된 내장 서버내 리액트 폴더
    String path = "C:\\Users\\504\\Desktop\\springweb\\Springwebboy\\src\\main\\resources\\static\\static\\media\\";
    @Transactional
    public Boolean write(RoomDto roomDto){
        //1. 등록한 유저
        String loginMemail = memberService.getloginMno();
        if(loginMemail == null){return false;}
        MemberEntity memberEntity = memberRepository.findByMemail(loginMemail).get();

        //2.룸등록
                //1. 룸저장
        RoomEntity roomEntity =  roomRepository.save(roomDto.toEntity());
        if(roomEntity.getRno() <1){return false;}
                //2. 룸에 회원 엔티티 대입  //3.회원엔티티에 룸 대입 [ 양방향 관계 ]
        roomEntity.setMemberEntity(memberEntity);
        memberEntity.getRoomEntityList().add(roomEntity);
        //3.사진 등록 저장
        roomDto.getRimg().forEach((img)->{

            if(!img.getOriginalFilename().equals("")) {
                RoomImgEntity roomImgEntity = roomImgRepository.
                        save(RoomImgEntity.builder().build() //필드가 적을때는 굳이 dto 필요 없음
                        );

                roomEntity.getRoomImgEntityList().add(roomImgEntity);
                roomImgEntity.setRoomEntity(roomEntity);

                try {
                    //첨부파일 사진 업로드
                    String fileName = img.getOriginalFilename();
                    roomImgEntity.setRimg(fileName);
                    File file = new File(path + fileName); // 경로 + 첨부파일명
                    img.transferTo(file);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
                //4.룸에 사진엔티티 대입    //5. 사진엔티티에 룸 대입

        return true;
    };

        //2. 룸 출력
        @Transactional
        public List<RoomDto> getroomlist(){
            // 1. 모든 룸 엔티티 꺼내기
            List<RoomEntity> roomEntityList = roomRepository.findAll(); // 매핑된 엔티티들
            List<RoomDto> roomDtoList = new ArrayList<>();              // 출력용 디티오
            // 2. 형변환
            roomEntityList.forEach( (e) -> {  roomDtoList.add(  e.toDto() ); });
            return roomDtoList;
        }

}
