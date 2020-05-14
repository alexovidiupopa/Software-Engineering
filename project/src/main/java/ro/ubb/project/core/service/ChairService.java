package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Chair;

import java.util.List;

public interface ChairService {

    List<Chair> getAllChairs();

    void addChair(Chair chair);

    void deleteChair(Chair chair);

    void updateChair(Chair chair);

    boolean isChair(int uid);
}
