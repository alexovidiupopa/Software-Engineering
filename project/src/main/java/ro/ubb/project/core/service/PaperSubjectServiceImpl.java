package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.PaperSubject;
import ro.ubb.project.core.repository.PaperSubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaperSubjectServiceImpl implements PaperSubjectService {

    @Autowired
    private PaperSubjectRepository paperSubjectRepository;

    @Override
    public List<PaperSubject> getAllPaperSubjects() {
        return this.paperSubjectRepository.findAll();
    }

    @Override
    public void addPaperSubject(PaperSubject paperSubject) {
        this.paperSubjectRepository.save(paperSubject);
    }

    @Override
    public void deletePaperSubject(PaperSubject paperSubject) {
        this.paperSubjectRepository.delete(paperSubject);
    }

    @Override
    public void updatePaperSubject(PaperSubject paperSubject) {
        Optional<PaperSubject> toUpdate = this.paperSubjectRepository.findAll()
                .stream()
                .filter(ps -> ps.getPid() == paperSubject.getPid() && ps.getKid() == paperSubject.getKid())
                .findAny();
        if (toUpdate.isPresent()) {
            PaperSubject ps = toUpdate.get();
            this.paperSubjectRepository.save(ps);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }
}
