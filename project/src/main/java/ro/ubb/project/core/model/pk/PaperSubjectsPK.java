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
public class PaperSubjectsPK implements Serializable {
    @Id
    private int pid;
    @Id
    private int kid;

}
