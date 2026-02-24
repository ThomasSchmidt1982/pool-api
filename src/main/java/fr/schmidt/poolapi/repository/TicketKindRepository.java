package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.TicketKind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketKindRepository extends JpaRepository<TicketKind, Long> {
}
