package ro.ubb.project.web.request;

import lombok.*;
import ro.ubb.project.web.dto.PaymentDataDto;
import ro.ubb.project.web.dto.TicketDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Data
public class TicketPurchaseRequest {

    private List<TicketDto> tickets;
    private PaymentDataDto paymentData;
}
