package ro.ubb.project.web.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRequest {
    private String message;
}
