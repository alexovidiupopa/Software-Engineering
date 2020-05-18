package ro.ubb.project.web.request;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BiddingRequest {
    private int userId;
    private ArrayList<Integer> accepted;
}
