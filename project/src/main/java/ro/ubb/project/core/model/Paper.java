package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Paper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private int aid;
    private int session;
    private String title;
    private String accepted;
    @Column(name="abstracturl")
    private String abstractUrl;
    @Column(name="contenturl")
    private String contentUrl;
}
