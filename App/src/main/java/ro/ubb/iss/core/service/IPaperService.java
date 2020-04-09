package ro.ubb.iss.core.service;

import ro.ubb.iss.core.model.PaperEntity;

import java.util.List;
import java.util.Optional;

public interface IPaperService {
    Optional<PaperEntity> findById(int id);

    List<PaperEntity> findAll();

    PaperEntity updatePaper(PaperEntity paper);

    void submitAbstract(String paper, String content);
}
