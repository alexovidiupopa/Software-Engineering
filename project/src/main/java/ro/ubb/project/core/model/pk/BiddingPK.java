package ro.ubb.project.core.model.pk;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class BiddingPK implements Serializable {
    @Id
    private int pcid;
    @Id
    private int pid;
}
