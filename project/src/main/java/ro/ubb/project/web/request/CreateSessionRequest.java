package ro.ubb.project.web.request;

import lombok.Data;
import lombok.ToString;
import ro.ubb.project.web.dto.PaperDto;

import java.util.ArrayList;

@Data
@ToString
public class CreateSessionRequest {
    private int rid;
    private int cid;
    private String time;
    private ArrayList<PaperDto> papers;
}
