package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();

    void addTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    void updateTicket(Ticket ticket);
}
