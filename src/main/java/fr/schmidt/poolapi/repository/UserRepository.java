package fr.schmidt.poolapi.repository;


import fr.schmidt.poolapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{

    Long Id(Long id);
    Optional<User> findByEmail(String email);
    List<User> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);

}
