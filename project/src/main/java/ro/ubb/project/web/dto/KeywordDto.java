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
public class KeywordDto implements Serializable {

    private int kid;
    private String name;
}
