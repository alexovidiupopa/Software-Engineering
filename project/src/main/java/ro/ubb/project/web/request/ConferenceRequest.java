package ro.ubb.project.web.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConferenceRequest {

    private String conferenceName;

    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String preliminaryPhaseDeadline;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String firstPhaseDeadline;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String secondPhaseDeadline;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String thirdPhaseDeadline;

}