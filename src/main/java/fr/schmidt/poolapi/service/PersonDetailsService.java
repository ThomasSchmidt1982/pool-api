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
        return userRepository.findByEmail(email)
                .or(() -> employeeRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
