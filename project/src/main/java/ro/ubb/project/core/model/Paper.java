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
public class Paper {
    @Id
    private int pid;
    private String topic;
    private String accepted;
    private String abstracturl;
    private String contenturl;
    private String presentationurl;
}
