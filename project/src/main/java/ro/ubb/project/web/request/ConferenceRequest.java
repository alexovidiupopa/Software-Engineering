package ro.ubb.project.web.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConferenceRequest {
    //private int conf_id;
    private String conferenceName;

    @Override
    public String toString() {
        return "ConferenceRequest{" +
                "conferenceName='" + conferenceName + '\'' +
                '}';
    }

    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String preliminaryPhaseDeadline;
    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String firstPhaseDeadline;
    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String secondPhaseDeadline;
    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String thirdPhaseDeadline;

}
