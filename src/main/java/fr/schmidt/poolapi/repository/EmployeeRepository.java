package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.Employee;
import fr.schmidt.poolapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<? extends User> findByEmail(String email);
}
