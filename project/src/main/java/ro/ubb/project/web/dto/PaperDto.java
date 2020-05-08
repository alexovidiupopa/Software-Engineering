package ro.ubb.project.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaperDto {
    private int pid;
    private int session;
    private String topic;
    private String accepted;
    private String abstracturl;
    private String contenturl;
    private String presentationurl;
}
