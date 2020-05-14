package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Keyword;

import java.util.List;

public interface KeywordService {

    List<Keyword> getAllKeywords();

    void addKeyword(Keyword keyword);

    void deleteKeyword(Keyword keyword);

    void updateKeyword(Keyword keyword);

    Keyword getKeywordById(int id);

    int getIdByName(String keyword);
}
