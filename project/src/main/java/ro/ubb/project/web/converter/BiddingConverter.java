package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Bidding;
import ro.ubb.project.web.dto.BiddingDto;

@Component
public class BiddingConverter extends AbstractConverter<Bidding, BiddingDto> implements Converter<Bidding, BiddingDto> {

    @Override
    public Bidding dtoToModel(BiddingDto biddingDto) {
        return Bidding.builder()
                .pcid(biddingDto.getPcid())
                .pid(biddingDto.getPid())
                .build();
    }

    @Override
    public BiddingDto modelToDto(Bidding bidding) {
        return BiddingDto.builder()
                .pcid(bidding.getPcid())
                .pid(bidding.getPid())
                .build();
    }
}
