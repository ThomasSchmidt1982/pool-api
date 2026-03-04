package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.Access;
import fr.schmidt.poolapi.model.enums.InOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRepository extends JpaRepository <Access, Long>{

    Optional<Access> findTopByUserIdOrderByTimestampDesc(Long userId);

    Integer countByInOut(InOut inOut);

}
