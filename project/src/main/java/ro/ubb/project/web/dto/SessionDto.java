package ro.ubb.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SessionDto {
    private int sid;
    private Time time;
    private int rid;
    private int supervisor;
    private int price;
}
