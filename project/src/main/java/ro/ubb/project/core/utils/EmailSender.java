package ro.ubb.project.core.utils;

import com.sendgrid.*;

import java.io.IOException;

public class EmailSender {
    public static final String ORIGIN_EMAIL = "pdie2597@cs.ubbcluj.ro";
    public static final String WELCOME_SUBJECT = "Welcome to CMS!";
    public static final String JOIN_SUBJECT = "Conference Invitation";
    public static final String PURCHASE_SUBJECT = "Your Purchase";
    public static final String REGISTER_LINK = "http://localhost:4200/api/pc/signup";
    public static final String LOGIN_LINK = "http://localhost:4200/api/login";

    public static void send(String from, String to, String subj, String msg) {
        Email sender = new Email(from);
        Email recipient = new Email(to);
        Content content = new Content("text/plain", msg);
        Mail mail = new Mail(sender, subj, recipient, content);
        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println("Email successfully sent!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
