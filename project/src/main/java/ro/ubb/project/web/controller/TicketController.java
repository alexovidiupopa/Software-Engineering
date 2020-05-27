package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.model.Ticket;
import ro.ubb.project.core.service.TicketService;
import ro.ubb.project.core.utils.EmailSender;
import ro.ubb.project.web.converter.TicketsConverter;
import ro.ubb.project.web.request.PayCartRequest;
import ro.ubb.project.web.request.TicketPurchaseRequest;
import ro.ubb.project.web.response.MessageResponse;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketsConverter converter;

    @RequestMapping(value = "/payCart", method = RequestMethod.POST)
    MessageResponse payCart(@RequestBody PayCartRequest paymentCart) {
        EmailSender.send(EmailSender.ORIGIN_EMAIL, paymentCart.getEmail(), EmailSender.PURCHASE_SUBJECT, EmailSender.TICKETS_MSG  + "\n" + paymentCart.getTickets().toString());
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    MessageResponse buy(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) {
        try {
            ticketPurchaseRequest.getTickets().forEach(ticketsDto -> {
                        List<Ticket> tickets = converter.dtoToModel(ticketsDto);
                        tickets.forEach(t -> this.ticketService.addTicket(t));
            });
            EmailSender.send(EmailSender.ORIGIN_EMAIL, ticketPurchaseRequest.getEmail(), EmailSender.PURCHASE_SUBJECT,EmailSender.TICKETS_MSG  + "\n" + ticketPurchaseRequest.getTickets().toString());
            return new MessageResponse("true");
        }
        catch (RuntimeException e){
            return new MessageResponse("false");
        }
    }
}
