package ro.ubb.project.web.request;


import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConferenceRequest {
    private int conf_id;
    private String name;
    private DateTime preliminaryPhaseDeadline;
    private DateTime firstPhaseDeadline;
    private DateTime secondPhaseDeadline;
    private DateTime thirdPhaseDeadline;
}
