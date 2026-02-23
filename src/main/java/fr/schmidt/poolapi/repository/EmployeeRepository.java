package fr.schmidt.poolapi.repository;

import fr.schmidt.poolapi.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
