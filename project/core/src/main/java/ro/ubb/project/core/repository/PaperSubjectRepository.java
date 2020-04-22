package ro.ubb.project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.project.core.model.PaperSubject;

@Repository
public interface PaperSubjectRepository extends JpaRepository<PaperSubject, Integer> {
}
