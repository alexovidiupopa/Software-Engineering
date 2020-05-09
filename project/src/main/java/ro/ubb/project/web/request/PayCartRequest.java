package ro.ubb.project.web.request;

import lombok.*;
import ro.ubb.project.web.dto.TicketDto;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PayCartRequest {
    private ArrayList<TicketDto> tickets;
    private String email;
}
