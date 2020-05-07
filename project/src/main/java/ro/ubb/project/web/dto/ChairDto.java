package ro.ubb.project.web.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
public class ChairDto implements Serializable {
    private int cid;
    private int uid;
}
