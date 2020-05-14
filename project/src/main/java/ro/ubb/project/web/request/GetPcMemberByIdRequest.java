package ro.ubb.project.web.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetPcMemberByIdRequest {
    private int pcid;
}
