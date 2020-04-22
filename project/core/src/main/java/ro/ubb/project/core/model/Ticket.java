package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Ticket {
    @Id
    private int tid;
    private BigDecimal price;
    private Integer seatno;
    private Date datepurchased;
    private String name;

}
