package ro.ubb.project.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.utils.EmailSender;
import ro.ubb.project.web.request.PayCartRequest;
import ro.ubb.project.web.request.TicketPurchaseRequest;
import ro.ubb.project.web.response.MessageResponse;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @RequestMapping(value = "payCart", method = RequestMethod.POST)
    MessageResponse payCart(@RequestBody PayCartRequest paymentCart) {
        EmailSender.send(EmailSender.ORIGIN_EMAIL, paymentCart.getEmail(), "Your Purchase", paymentCart.getTickets().toString());
        return new MessageResponse("success");
    }

    @RequestMapping(value = "buy", method = RequestMethod.POST)
    MessageResponse buy(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) {
        EmailSender.send(EmailSender.ORIGIN_EMAIL, ticketPurchaseRequest.getEmail(), "Your Purchase", ticketPurchaseRequest.getTickets().toString());
        return new MessageResponse("success");
    }
}
