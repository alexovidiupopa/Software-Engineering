package ro.ubb.project.core.model;

import lombok.*;
import ro.ubb.project.core.model.pk.BiddingPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@IdClass(BiddingPK.class)
public class Bidding {
    @Id
    private int pcid;
    @Id
    private int pid;
}
