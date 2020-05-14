package ro.ubb.project.core.service;

import ro.ubb.project.core.model.PaperSubject;

import java.util.List;

public interface PaperSubjectService {

    List<PaperSubject> getAllPaperSubjects();

    void addPaperSubject(PaperSubject paperSubject);

    void deletePaperSubject(PaperSubject paperSubject);

    void updatePaperSubject(PaperSubject paperSubject);
}
