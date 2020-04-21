package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

   // private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void addPerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public Person getPersonByUserName(String username) {
        return null;
    }
}
