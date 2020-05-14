package ro.ubb.project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.project.core.model.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
}
