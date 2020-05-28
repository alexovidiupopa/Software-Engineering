package ro.ubb.project.core.model;

import lombok.*;
import ro.ubb.project.core.model.pk.BiddingPK;

import javax.persistence.Column;
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
public class Assignment {

    @Id
    private int pcid;
    @Id
    private int pid;
    private int qualifier;

    @Column(name="reviewurl")
    private String reviewUrl;
}
