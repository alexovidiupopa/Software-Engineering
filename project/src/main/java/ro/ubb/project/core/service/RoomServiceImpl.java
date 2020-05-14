package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Room;
import ro.ubb.project.core.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @Override
    public void addRoom(Room room) {
        this.roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Room room) {
        this.roomRepository.delete(room);
    }

    @Override
    public void deleteRoomById(int rid) {
        this.roomRepository.deleteById(rid);
    }

    @Override
    public void updateRoom(Room room) {
        Optional<Room> toUpdate = this.roomRepository.findById(room.getRid());
        if (toUpdate.isPresent()) {
            Room r = toUpdate.get();
            r.setCapacity(room.getCapacity());
            this.roomRepository.save(r);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }
}
