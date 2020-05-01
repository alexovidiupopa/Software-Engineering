package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.core.repository.PaperRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public List<Paper> getAllPapers() {
        return this.paperRepository.findAll();
    }

    @Override
    public void addPaper(Paper paper) {
        this.paperRepository.save(paper);

    }

    @Override
    public void deletePaper(Paper paper) {
        this.paperRepository.delete(paper);

    }

    @Override
    public void updatePaper(Paper paper) {
        Optional<Paper> toUpdate = this.paperRepository.findById(paper.getPid());
        if(toUpdate.isPresent()) {
            Paper p = toUpdate.get();
            p.setTopic(paper.getTopic());
            p.setAccepted(paper.getAccepted());
            p.setAbstracturl(paper.getAbstracturl());
            p.setContenturl(paper.getContenturl());
            p.setPresentationurl(paper.getPresentationurl());
            this.paperRepository.save(p);
        }
        else{
            throw new RuntimeException("No assignment found");
        }
    }

    @Override
    public Paper getPaperById(int id) {
        Optional<Paper> paper = this.paperRepository.findById(id);
        if (paper.isPresent())
            return paper.get();
        else
            throw new RuntimeException("No paper found");
    }
}
