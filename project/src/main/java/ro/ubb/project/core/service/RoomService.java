package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    void addRoom(Room room);

    void deleteRoom(Room room);

    void deleteRoomById(int rid);

    void updateRoom(Room room);
}
