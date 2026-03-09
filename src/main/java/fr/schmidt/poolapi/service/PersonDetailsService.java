package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.repository.EmployeeRepository;
import fr.schmidt.poolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Cherche d'abord dans les users
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) return user.get();

        // puis cherche dans les employee
        var employee = employeeRepository.findByEmail(email);
        if (employee.isPresent()) return employee.get();

        throw new UsernameNotFoundException("User not found: " + email);
    }
}