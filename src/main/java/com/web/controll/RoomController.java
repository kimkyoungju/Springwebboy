package com.web.controll;


import com.web.domain.dto.RoomDto;
import com.web.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/setroom")
    public boolean write(RoomDto roomDto){
        System.out.println(roomDto.toString());
        return roomService.write(roomDto);


    }
    @GetMapping("/getroomlist")
    public List<RoomDto> getroomlist(){ return roomService.getroomlist();}

}
