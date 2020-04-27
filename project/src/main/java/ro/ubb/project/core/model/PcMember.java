package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pcmember", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PcMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pcid;

    private int uid;
}
