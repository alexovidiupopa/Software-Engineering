package ro.ubb.iss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.core.model.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
