package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Assignment;

import java.util.List;

public interface AssignmentService {

    List<Assignment> getAllAssignments();

    Assignment getAssignmentById(int pcid, int pid);

    void addAssignment(Assignment assignment);

    void deleteAssignment(Assignment assignment);

    void updateAssignment(Assignment assignment);

    List<Integer> getReviewersForPaperId(Integer pid);

    List<Integer> getPapersForReviewer(Integer pcid);
}
