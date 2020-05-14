package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Paper;

import java.util.List;

public interface PaperService {

    List<Paper> getAllPapers();

    void addPaper(Paper paper);

    void deletePaper(Paper paper);

    void updatePaper(Paper paper);

    Paper getPaperById(int id);

    List<Paper> getPapersOfAuthor(Integer id);
}
