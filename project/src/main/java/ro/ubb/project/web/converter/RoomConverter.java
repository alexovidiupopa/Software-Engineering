package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Room;
import ro.ubb.project.web.dto.RoomDto;

@Component
public class RoomConverter extends AbstractConverter<Room, RoomDto> {
    @Override
    public Room dtoToModel(RoomDto roomDto) {
        return Room.builder()
                .rid(roomDto.getRid())
                .capacity(roomDto.getCapacity())
                .build();
    }

    @Override
    public RoomDto modelToDto(Room room) {
        return RoomDto.builder()
                .rid(room.getRid())
                .capacity(room.getCapacity())
                .build();
    }
}
