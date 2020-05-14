package ro.ubb.project.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.project.web.dto.RoomDto;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomsResponse {
    private ArrayList<RoomDto> rooms;
}
