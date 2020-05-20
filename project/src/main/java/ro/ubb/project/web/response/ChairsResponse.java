package ro.ubb.project.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.ubb.project.web.dto.ChairDto;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class ChairsResponse {
    private ArrayList<ChairDto> chairs;
}
