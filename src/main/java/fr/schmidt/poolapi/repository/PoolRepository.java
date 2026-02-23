package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool, Long> {
}
