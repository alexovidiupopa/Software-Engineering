package ro.ubb.project.core.model;

import lombok.*;
import ro.ubb.project.core.model.pk.PaperSubjectsPK;

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
@IdClass(PaperSubjectsPK.class)
public class PaperSubject {
    @Id
    private int pid;
    @Id
    private int kid;
}
