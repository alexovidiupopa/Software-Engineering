package ro.ubb.project.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.service.*;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.project.core");

        AssignmentService assignmentService = context.getBean(AssignmentService.class);
        AuthorService authorService = context.getBean(AuthorService.class);
        BiddingService biddingService = context.getBean(BiddingService.class);
        ChairService chairService = context.getBean(ChairService.class);
        KeywordService keywordService = context.getBean(KeywordService.class);
        PaperService paperService = context.getBean(PaperService.class);
        PaperSubjectService paperSubjectService = context.getBean(PaperSubjectService.class);
        PcMemberService pcMemberService = context.getBean(PcMemberService.class);
        PersonService personService = context.getBean(PersonService.class);
        RoomService roomService = context.getBean(RoomService.class);
        SessionService sessionService = context.getBean(SessionService.class);
        TicketService ticketService = context.getBean(TicketService.class);

        personService.updatePerson(new Person(12, "12", "12", "12", "12",
                "12", "12", "12", "12", "12"));

        assignmentService.getAllAssignments().forEach(System.out::println);
        authorService.getAllAuthors().forEach(System.out::println);
        biddingService.getAllBiddings().forEach(System.out::println);
        chairService.getAllChairs().forEach(System.out::println);
        keywordService.getAllKeywords().forEach(System.out::println);
        paperService.getAllPapers().forEach(System.out::println);
        paperSubjectService.getAllPaperSubjects().forEach(System.out::println);
        pcMemberService.getAllPcMembers().forEach(System.out::println);
        personService.getAllPersons().forEach(System.out::println);
        roomService.getAllRooms().forEach(System.out::println);
        sessionService.getAllSessions().forEach(System.out::println);
        ticketService.getAllTickets().forEach(System.out::println);
    }
}
