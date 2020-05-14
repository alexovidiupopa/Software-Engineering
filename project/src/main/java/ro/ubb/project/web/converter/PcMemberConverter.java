package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.PcMember;
import ro.ubb.project.web.dto.PcMemberDto;

@Component
public class PcMemberConverter extends AbstractConverter<PcMember, PcMemberDto> implements Converter<PcMember, PcMemberDto> {
    @Override
    public PcMember dtoToModel(PcMemberDto pcMemberDto) {
        return PcMember.builder()
                .pcid(pcMemberDto.getPcid())
                .uid(pcMemberDto.getUid())
                .build();
    }

    @Override
    public PcMemberDto modelToDto(PcMember pcMember) {
        return PcMemberDto.builder()
                .pcid(pcMember.getPcid())
                .uid(pcMember.getUid())
                .build();
    }

}
