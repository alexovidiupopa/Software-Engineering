package ro.ubb.project.core.service;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Scheduler {
    private String conferenceName;
    /*@JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String preliminaryPhaseDeadline;
    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String firstPhaseDeadline;
    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String secondPhaseDeadline;
    @JsonFormat(pattern="MM/DD/YYYY HH:mm:ss")
    private String thirdPhaseDeadline;*/

    /*public String getCurrentPhase(){
        if (firstPhaseDeadline.isBeforeNow()){
            return "first";
        }
        if (secondPhaseDeadline.isBeforeNow()){
            return "second";
        }
        if(thirdPhaseDeadline.isBeforeNow()){
            return "third";
        }
        return "finished";
    }*/
}
