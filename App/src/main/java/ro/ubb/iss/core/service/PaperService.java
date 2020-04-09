package ro.ubb.iss.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.iss.core.model.PaperEntity;
import ro.ubb.iss.core.repository.PaperRepository;

import java.util.List;
import java.util.Optional;

public class PaperService implements IPaperService{

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Optional<PaperEntity> findById(int id) {
        return paperRepository.findById(id);
    }

    @Override
    public List<PaperEntity> findAll() {
        return paperRepository.findAll();
    }

    @Override
    public PaperEntity updatePaper(PaperEntity paper) {
        return paperRepository.save(paper);
    }

    @Override
    public void submitAbstract(String paper, String content) {

    }
}
