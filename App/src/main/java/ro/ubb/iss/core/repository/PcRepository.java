package ro.ubb.iss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.core.model.PcEntity;

public interface PcRepository extends JpaRepository<PcEntity, Integer> {
}
