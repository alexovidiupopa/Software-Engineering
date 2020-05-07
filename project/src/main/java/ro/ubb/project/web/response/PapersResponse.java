package ro.ubb.project.web.response;

import lombok.*;
import ro.ubb.project.web.dto.PaperDto;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PapersResponse {
    private ArrayList<PaperDto> papers;
}
