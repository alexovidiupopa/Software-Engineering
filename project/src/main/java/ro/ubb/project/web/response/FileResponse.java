package ro.ubb.project.web.response;

import lombok.*;
import org.springframework.core.io.Resource;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FileResponse implements Serializable {
    Resource file;
}
