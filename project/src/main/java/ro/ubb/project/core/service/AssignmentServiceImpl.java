package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.project.core.model.Assignment;
import ro.ubb.project.core.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> getAllAssignments() {
        return this.assignmentRepository.findAll();
    }

    @Override
    public Assignment getAssignmentById(int pcid, int pid) {
        Optional<Assignment> assignment = this.assignmentRepository.findAll().stream()
                .filter(a -> a.getPcid() == pcid)
                .filter(a -> a.getPid() == pid)
                .findAny();
        if (assignment.isPresent())
            return assignment.get();
        else
            throw new RuntimeException("No paper found");
    }

    @Override
    public void addAssignment(Assignment assignment) {
        this.assignmentRepository.save(assignment);
    }

    @Override
    public void deleteAssignment(Assignment assignment) {
        this.assignmentRepository.delete(assignment);
    }

    @Override
    @Transactional
    public void updateAssignment(Assignment assignment) {
        Optional<Assignment> toUpdate = this.assignmentRepository.findAll()
                .stream()
                .filter(a -> a.getPcid() == assignment.getPcid() && a.getPid() == assignment.getPid())
                .findAny();
        if (toUpdate.isPresent()) {
            Assignment a = toUpdate.get();
            a.setQualifier(assignment.getQualifier());
        } else {
            throw new RuntimeException("No assignment found");
        }
    }

    @Override
    public List<Integer> getReviewersForPaperId(Integer pid) {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignment -> assignment.getPid() == pid)
                .map(Assignment::getPcid)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getPapersForReviewer(Integer pcid) {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignment -> assignment.getPcid() == pcid)
                .map(Assignment::getPid)
                .collect(Collectors.toList());
    }
}
