package ro.ubb.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ReviewerDto {
    private int id;
    private String firstName, lastName;
}
