package ro.ubb.project.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.ubb.project.web.dto.ReviewerDto;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class ReviewersResponse {
    private ArrayList<ReviewerDto> reviewers;
}
