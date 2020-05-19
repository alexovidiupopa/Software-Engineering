package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Ticket;
import ro.ubb.project.web.dto.TicketDto;

@Component
public class TicketConverter extends AbstractConverter<Ticket, TicketDto> implements Converter<Ticket, TicketDto> {

    @Override
    public Ticket dtoToModel(TicketDto ticketDto) {
        return Ticket.builder()
                .tid(ticketDto.getTid())
                .price(ticketDto.getPrice())
                .seatno(ticketDto.getSeatno())
                .datepurchased(ticketDto.getDatepurchased())
                .name(ticketDto.getName())
                .sid(ticketDto.getSid())
                .build();
    }

    @Override
    public TicketDto modelToDto(Ticket ticket) {
        return TicketDto.builder()
                .tid(ticket.getTid())
                .price(ticket.getPrice())
                .seatno(ticket.getSeatno())
                .datepurchased(ticket.getDatepurchased())
                .name(ticket.getName())
                .sid(ticket.getSid())
                .build();
    }
}
