package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Ticket;
import ro.ubb.project.core.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return this.ticketRepository.findAll();
    }

    @Override
    public void addTicket(Ticket ticket) {
        this.ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        this.ticketRepository.delete(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Optional<Ticket> toUpdate = this.ticketRepository.findById(ticket.getTid());
        if (toUpdate.isPresent()) {
            Ticket t = toUpdate.get();
            t.setSeatno(ticket.getSeatno());
            t.setPrice(ticket.getPrice());
            t.setDatepurchased(ticket.getDatepurchased());
            t.setName(ticket.getName());
            this.ticketRepository.save(t);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }
}
