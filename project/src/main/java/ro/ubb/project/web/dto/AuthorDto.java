package ro.ubb.project.web.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
public class AuthorDto implements Serializable {
    private int aid;
}
