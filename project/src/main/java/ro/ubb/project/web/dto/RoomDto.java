package ro.ubb.project.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDto {
    private int rid;
    private int capacity;
}
