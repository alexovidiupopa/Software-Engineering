package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.service.RoomService;
import ro.ubb.project.web.converter.RoomConverter;
import ro.ubb.project.web.dto.RoomDto;
import ro.ubb.project.web.response.MessageResponse;
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
    RoomsResponse getAllRooms() {
        return new RoomsResponse((ArrayList<RoomDto>) converter.convertModelsToDtos(roomService.getAllRooms()));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    MessageResponse addRoom(@RequestBody RoomDto roomDto) {
        try {
            roomService.addRoom(converter.dtoToModel(roomDto));
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    MessageResponse delete(@PathVariable Integer id) {
        try {
            roomService.deleteRoomById(id);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }
}
