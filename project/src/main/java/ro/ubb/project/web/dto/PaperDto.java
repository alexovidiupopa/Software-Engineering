package ro.ubb.project.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class PaperDto {
    private int pid;
    private int authorId;
    private int session;
    private String title;
    private String accepted;
    private String abstractUrl;
    private String contentUrl;
    private String topic;
}
