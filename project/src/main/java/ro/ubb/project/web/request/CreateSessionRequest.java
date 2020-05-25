package ro.ubb.project.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.ubb.project.web.dto.PaperDto;

import java.util.ArrayList;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequest {
    private int rid;
    private int cid;
    private String time;
    private ArrayList<PaperDto> papers;
}
