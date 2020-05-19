package ro.ubb.project.web.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Data
public class TicketDto implements Serializable {

    private int tid;
    private BigDecimal price;
    private Integer seatno;
    private Date datepurchased;
    private String name;
    private int sid;
}
