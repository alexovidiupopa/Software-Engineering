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
public class PaperSubjectDto implements Serializable {

    private int pid;
    private int kid;
}
