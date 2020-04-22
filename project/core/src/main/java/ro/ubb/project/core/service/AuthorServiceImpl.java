package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Author;
import ro.ubb.project.core.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public void addAuthor(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public void updateAuthor(Author author) {
        Optional<Author> toUpdate = this.authorRepository.findById(author.getAid());
        if(toUpdate.isPresent()) {
            Author a = toUpdate.get();
            this.authorRepository.save(a);
        }
        else{
            throw new RuntimeException("No assignment found");
        }
    }
}
