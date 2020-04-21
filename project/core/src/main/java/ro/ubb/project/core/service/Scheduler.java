package ro.ubb.project.core.service;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class Scheduler {
    private String conferenceName;
    private DateTime firstPhaseDeadline;
    private DateTime secondPhaseDeadline;
    private DateTime thirdPhaseDeadline;

    public String getCurrentPhase(){
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
    }
}
