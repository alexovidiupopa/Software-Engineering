package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Ticket;
import ro.ubb.project.web.dto.TicketsDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketsConverter extends AbstractConverter<List<Ticket>, TicketsDto> implements Converter<List<Ticket>, TicketsDto> {

    @Override
    public List<Ticket> dtoToModel(TicketsDto ticketsDto) {
        List<Ticket> tickets = new ArrayList<>();
        ticketsDto.getSessions().forEach(s -> tickets.add(
                Ticket.builder()
                .tid(ticketsDto.getTid())
                .price(ticketsDto.getPrice())
                .seatno(ticketsDto.getSeatno())
                .datepurchased(ticketsDto.getDatepurchased())
                .name(ticketsDto.getName())
                .sid(s)
                .build()
        ));
        return tickets;
    }

    @Override
    public TicketsDto modelToDto(List<Ticket> tickets) {
        return null;
    }
}
