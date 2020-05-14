package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Keyword;
import ro.ubb.project.core.repository.KeywordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public List<Keyword> getAllKeywords() {
        return this.keywordRepository.findAll();
    }

    @Override
    public void addKeyword(Keyword keyword) {
        this.keywordRepository.save(keyword);
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        this.keywordRepository.delete(keyword);
    }

    @Override
    public void updateKeyword(Keyword keyword) {
        Optional<Keyword> toUpdate = this.keywordRepository.findById(keyword.getKid());
        if (toUpdate.isPresent()) {
            Keyword k = toUpdate.get();
            k.setName(keyword.getName());
            this.keywordRepository.save(k);
        } else {
            throw new RuntimeException("No keyword found");
        }
    }

    @Override
    public Keyword getKeywordById(int id) {
        Optional<Keyword> keyword = this.keywordRepository.findById(id);
        if (keyword.isPresent()) {
            return keyword.get();
        } else {
            throw new RuntimeException("No keyword found");
        }
    }

    @Override
    public int getIdByName(String keyword) {
        for (Keyword key : keywordRepository.findAll())
            if (key.getName().equals(keyword))
                return key.getKid();
        return -1;
    }
}
