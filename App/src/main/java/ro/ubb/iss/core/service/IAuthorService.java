package ro.ubb.iss.core.service;

import ro.ubb.iss.core.model.AuthorEntity;

import java.util.List;

public interface IAuthorService {

    List<AuthorEntity> findAll();
}
