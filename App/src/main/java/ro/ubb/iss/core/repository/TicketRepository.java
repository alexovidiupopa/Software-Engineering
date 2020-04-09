package ro.ubb.iss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.core.model.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}
