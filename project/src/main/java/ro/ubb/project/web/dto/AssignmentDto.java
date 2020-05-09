package ro.ubb.project.web.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Data
public class AssignmentDto implements Serializable {

    private int pcid;
    private int pid;
    private int qualifier;
}
