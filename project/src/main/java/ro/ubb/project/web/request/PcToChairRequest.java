package ro.ubb.project.web.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PcToChairRequest {
    private int pcid;
}
