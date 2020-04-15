package ro.ubb.project.web.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConferenceRequest {
    private String name;
    private String preliminaryPhaseDeadline;
    private String firstPhaseDeadline;
    private String secondPhaseDeadline;
    private String thirdPhaseDeadline;
}
