package ro.ubb.project.web.request;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PaperRequest {
    private int paperId;
    private int authorId;
    private String paperTitle;
    private ArrayList<String> keywords;
}
