package ro.ubb.project.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Data
public class PaymentDataDto {

    private String cardNumber;
    private String expirationDate;
    private String holderName;
    private String ccv;
}
