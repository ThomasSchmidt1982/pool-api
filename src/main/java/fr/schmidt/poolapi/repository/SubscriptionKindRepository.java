package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.SubscriptionKind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionKindRepository extends JpaRepository<SubscriptionKind, Long> {
}
