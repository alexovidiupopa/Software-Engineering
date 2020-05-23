package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    @Override
    public void addPerson(Person person) {
        this.personRepository.save(person);

    }

    @Override
    public void deletePerson(Person person) {
        this.personRepository.delete(person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        Optional<Person> toUpdate = this.personRepository.findById(person.getUid());
        if (toUpdate.isPresent()) {
            Person p = toUpdate.get();
            p.setUsername(person.getUsername());
            p.setPassword(person.getPassword());
            p.setWebsite(person.getWebsite());
            p.setAffiliation(person.getAffiliation());
            p.setFirstname(person.getFirstname());
            p.setLastname(person.getLastname());
            p.setPhonenumber(person.getPhonenumber());
            p.setEmail(person.getEmail());
            p.setAcademicrank(person.getAcademicrank());
            this.personRepository.save(p);
        } else {
            throw new RuntimeException("No person with this name found");
        }
    }

    @Override
    public Person getPersonByUserName(String username) {
        Optional<Person> result = this.personRepository.findAll()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .findAny();
        if (result.isPresent()) {
            System.out.println(result.get().getUid());
            return result.get();
        } else {
            throw new RuntimeException("No person with this name found");
        }
    }

    @Override
    public Person getPersonById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent())
            return person.get();
        throw new RuntimeException("No person with this id");
    }
}
