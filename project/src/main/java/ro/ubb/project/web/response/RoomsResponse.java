package ro.ubb.project.web.response;

import lombok.*;
import ro.ubb.project.web.dto.RoomDto;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomsResponse {
    private ArrayList<RoomDto> rooms;
}
