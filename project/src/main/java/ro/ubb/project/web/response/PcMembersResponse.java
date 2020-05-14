package ro.ubb.project.web.response;

import lombok.*;
import ro.ubb.project.web.dto.PcMemberDto;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PcMembersResponse {
    private ArrayList<PcMemberDto> pcMember;
}