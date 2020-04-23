package ro.ubb.project.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.service.PersonService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.project.core");

        PersonService personService = context.getBean(PersonService.class);
        personService.getAllPersons().forEach(System.out::println);

        Person p = new Person();
        System.out.println(p.getUsername());
    }
}
