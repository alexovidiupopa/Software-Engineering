package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();
    void addAuthor(Author author);
    void deleteAuthor(Author author);
    void updateAuthor(Author author);

    boolean isAuthor(int uid);
}
