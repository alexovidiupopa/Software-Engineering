package ro.ubb.project.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    private int pid;
    private String topic;
    private String accepted;
    private String abstracturl;
    private String contenturl;
    private String presentationurl;
}
