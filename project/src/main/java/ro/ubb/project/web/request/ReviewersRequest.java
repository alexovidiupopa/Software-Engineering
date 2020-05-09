package ro.ubb.project.web.request;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ReviewersRequest {
    private int pid;
    private ArrayList<Integer> reviewers;
}
