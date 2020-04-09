package ro.ubb.iss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.core.model.PaperEntity;

public interface PaperRepository extends JpaRepository<PaperEntity, Integer> {
}
