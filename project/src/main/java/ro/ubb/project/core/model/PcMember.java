package ro.ubb.project.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
