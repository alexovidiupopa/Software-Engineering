package ro.ubb.iss.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.iss.core.model.AuthorEntity;
import ro.ubb.iss.core.model.PersonEntity;
import ro.ubb.iss.core.service.IAuthorService;
import ro.ubb.iss.core.service.IPersonService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.iss.core");

        IAuthorService authorService = context.getBean(IAuthorService.class);
        IPersonService personService = context.getBean(IPersonService.class);



        List<AuthorEntity> lst = authorService.findAll();
        System.out.println(lst);

        List<PersonEntity> prsn = personService.findAll();
        prsn.forEach(System.out::println);
    }
}
