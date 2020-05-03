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
public class AuthorDto implements Serializable {
    private int aid;
    private int uid;
}
