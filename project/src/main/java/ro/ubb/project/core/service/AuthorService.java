package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    void addAuthor(Author author);

    void deleteAuthor(Author author);

    void updateAuthor(Author author);

    Author getAuthorById(int id);

    boolean isAuthor(int uid);

    Author getAuthorByUid(Integer id);
}
