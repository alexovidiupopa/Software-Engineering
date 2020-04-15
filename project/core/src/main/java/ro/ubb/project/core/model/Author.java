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
public class Author  {
    @Id
    private int aid;

}
