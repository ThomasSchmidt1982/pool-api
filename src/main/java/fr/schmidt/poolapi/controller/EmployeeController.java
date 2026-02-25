package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.EmployeeRequest;
import fr.schmidt.poolapi.dto.response.EmployeeResponse;
import fr.schmidt.poolapi.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    //injection de dépendance
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponse> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @PostMapping
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest request){
        return employeeService.create(request);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request){
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id){
        employeeService.desactivate(id);
    }
}
