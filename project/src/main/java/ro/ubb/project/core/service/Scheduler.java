package ro.ubb.project.core.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Scheduler {
    private String conferenceName;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String preliminaryPhaseDeadline;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String firstPhaseDeadline;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String secondPhaseDeadline;
    @JsonFormat(pattern = "MM/DD/YYYY HH:mm:ss")
    private String thirdPhaseDeadline;

    public String getCurrentPhase() {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
        if (LocalDateTime.parse(preliminaryPhaseDeadline, dtf).toDateTime().isAfterNow()) {
            return "preliminary";
        }
        if (LocalDateTime.parse(preliminaryPhaseDeadline, dtf).toDateTime().isBeforeNow()) {
            return "first";
        }
        if (LocalDateTime.parse(firstPhaseDeadline, dtf).toDateTime().isBeforeNow()) {
            return "second";
        }
        if (LocalDateTime.parse(secondPhaseDeadline, dtf).toDateTime().isBeforeNow()) {
            return "third";
        }
        return "finished";
    }

    public void updateCurrentDeadline(DateTime parseDateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
        if (parseDateTime.isBefore(DateTime.parse(preliminaryPhaseDeadline, dtf))) {
            preliminaryPhaseDeadline = parseDateTime.toString();
            return;
        }
        if (parseDateTime.isBefore(DateTime.parse(firstPhaseDeadline, dtf))) {
            firstPhaseDeadline = parseDateTime.toString();
            return;
        }
        if (parseDateTime.isBefore(DateTime.parse(secondPhaseDeadline, dtf))) {
            secondPhaseDeadline = parseDateTime.toString();
            return;
        }
        if (parseDateTime.isBefore(DateTime.parse(thirdPhaseDeadline, dtf))) {
            thirdPhaseDeadline = parseDateTime.toString();
        }
    }
}
