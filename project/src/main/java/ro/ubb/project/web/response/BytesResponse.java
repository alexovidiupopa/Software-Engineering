package ro.ubb.project.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BytesResponse {
    private byte[] bytes;
}
