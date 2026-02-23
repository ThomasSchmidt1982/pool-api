package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository <Access, Long>{
}
