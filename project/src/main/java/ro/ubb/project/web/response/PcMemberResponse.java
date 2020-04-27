package ro.ubb.project.web.response;

import lombok.*;
import ro.ubb.project.web.dto.PcMemberDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PcMemberResponse {
    private PcMemberDto pcMember;
}
