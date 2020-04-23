package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Room {
    @Id
    private int rid;
    private int capacity;
}
