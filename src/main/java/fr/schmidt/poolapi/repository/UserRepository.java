package fr.schmidt.poolapi.repository;


import fr.schmidt.poolapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{

}
