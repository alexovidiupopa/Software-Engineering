package ro.ubb.iss.core.service;

import ro.ubb.iss.core.model.PersonEntity;

import java.util.List;

public interface IPersonService {
    List<PersonEntity> findAll();
}
