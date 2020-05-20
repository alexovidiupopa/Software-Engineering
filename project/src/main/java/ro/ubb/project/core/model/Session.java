package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private Time time;
    private int rid;
    private int supervisor;
}
