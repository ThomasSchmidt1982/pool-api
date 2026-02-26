package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.EmployeeRequest;
import fr.schmidt.poolapi.dto.response.EmployeeResponse;
import fr.schmidt.poolapi.model.entity.Employee;
import fr.schmidt.poolapi.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    // injection de dépendance
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    // GET /employees
    public List<EmployeeResponse> findAll(){
        return employeeRepository.findAll()
                .stream()
                .map(employee -> toResponse(employee))
                .toList();
    }

    // GET /employees/:id
    public EmployeeResponse findById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(" Employee not found"));
        return toResponse(employee);
    }

    // POST /employees
    public EmployeeResponse create(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setLastname(employeeRequest.lastname());
        employee.setFirstname(employeeRequest.firstname());
        employee.setEmail(employeeRequest.email());
        employee.setPassword(passwordEncoder.encode(employeeRequest.password()));
        employee.setAdmin(employeeRequest.isAdmin());
        employee.setActive(employeeRequest.isActive());
        // sauvegarde en BDD et retourne la réponse
        return toResponse(employeeRepository.save(employee));
    }

    // PUT /employees/:id
    public EmployeeResponse update(Long id, EmployeeRequest employeeRequest){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        employee.setLastname(employeeRequest.lastname());
        employee.setFirstname(employeeRequest.firstname());
        employee.setEmail(employeeRequest.email());
        employee.setAdmin(employeeRequest.isAdmin());
        employee.setActive(employeeRequest.isActive());
        // Sauvegarde les modifs et retourne la réponse
        return toResponse(employeeRepository.save(employee));
    }

    // DELETE /employees/:id (soft delete)
    public void desactivate(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        employee.setActive(false);
        employeeRepository.save(employee);
    }

    // Mapping entity --> DTO
    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getLastname(),
                employee.getFirstname(),
                employee.getEmail(),
                employee.getPhone(),
                employee.isAdmin(),
                employee.isActive()
        );
    }

}
