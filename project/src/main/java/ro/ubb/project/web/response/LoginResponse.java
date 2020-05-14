package ro.ubb.project.web.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LoginResponse {
    private boolean success;
    private String type;
}
