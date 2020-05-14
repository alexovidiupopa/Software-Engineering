package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Chair;
import ro.ubb.project.core.repository.ChairRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChairServiceImpl implements ChairService {

    @Autowired
    private ChairRepository chairRepository;

    @Override
    public List<Chair> getAllChairs() {
        return this.chairRepository.findAll();
    }

    @Override
    public void addChair(Chair chair) {
        this.chairRepository.save(chair);
    }

    @Override
    public void deleteChair(Chair chair) {
        this.chairRepository.delete(chair);
    }

    @Override
    public void updateChair(Chair chair) {
        Optional<Chair> toUpdate = this.chairRepository.findById(chair.getCid());
        if (toUpdate.isPresent()) {
            Chair c = toUpdate.get();
            this.chairRepository.save(c);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }

    @Override
    public boolean isChair(int uid) {
        Optional<Chair> chair = this.chairRepository.findAll()
                .stream()
                .filter(c -> c.getUid() == uid)
                .findAny();
        return chair.isPresent();
    }
}
