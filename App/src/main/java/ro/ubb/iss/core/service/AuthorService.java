package ro.ubb.iss.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.core.model.AuthorEntity;
import ro.ubb.iss.core.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<AuthorEntity> findAll() {
        return null;
    }
}
