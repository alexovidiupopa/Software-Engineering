package ro.ubb.project.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.project.web.dto.PcMemberDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PcMemberResponse {
    private PcMemberDto pcMember;
}
