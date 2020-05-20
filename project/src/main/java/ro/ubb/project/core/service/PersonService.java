package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    void addPerson(Person person);

    void deletePerson(Person person);

    void updatePerson(Person person);

    Person getPersonByUserName(String username);

    Person getPersonById(int id);
}
