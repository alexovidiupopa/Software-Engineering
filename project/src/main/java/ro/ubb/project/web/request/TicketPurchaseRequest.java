package ro.ubb.project.web.request;

import lombok.*;
import ro.ubb.project.web.dto.PaymentDataDto;
import ro.ubb.project.web.dto.TicketsDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Data
public class TicketPurchaseRequest {

    private List<TicketsDto> tickets;
    private PaymentDataDto paymentData;
    private String email;
}
