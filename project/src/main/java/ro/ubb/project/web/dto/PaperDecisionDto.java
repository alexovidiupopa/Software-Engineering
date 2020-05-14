package ro.ubb.project.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Data
public class PaperDecisionDto {
    int pid;
    String decision;
}
