package ro.ubb.iss.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.core.model.PersonEntity;
import ro.ubb.iss.core.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }
}
