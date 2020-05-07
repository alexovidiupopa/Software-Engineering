package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.RoomService;
import ro.ubb.project.web.converter.RoomConverter;
import ro.ubb.project.web.dto.RoomDto;
import ro.ubb.project.web.response.RoomsResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomConverter converter;

    @RequestMapping(value = "/getAllRooms", method = RequestMethod.GET)
    RoomsResponse getAllRooms(){
        return new RoomsResponse((ArrayList<RoomDto>) converter.convertModelsToDtos(roomService.getAllRooms()));
    }
}
