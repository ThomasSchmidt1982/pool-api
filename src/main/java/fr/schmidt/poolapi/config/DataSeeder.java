package fr.schmidt.poolapi.config;

import com.github.javafaker.Faker;
import fr.schmidt.poolapi.model.entity.*;
import fr.schmidt.poolapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PoolRepository poolRepository;
    private final SubscriptionKindRepository subscriptionKindRepository;
    private final TicketKindRepository ticketKindRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() > 0) return; // évite de réinsérer à chaque démarrage

        Faker faker = new Faker(new Locale("fr"));

        // Pool
        Pool pool = new Pool();
        pool.setMaxCapacity(150);
        poolRepository.save(pool);

        // SubscriptionKinds
        subscriptionKindRepository.saveAll(List.of(
                createKind("Mensuel", 30, 50.0),
                createKind("Trimestriel", 90, 130.0),
                createKind("Annuel", 365, 450.0)
        ));

        // TicketKinds
        ticketKindRepository.saveAll(List.of(
                createTicketKind("Personnel", 10.0),
                createTicketKind("Duo", 15.0),
                createTicketKind("Famille", 25.0)
        ));

        // Users
        for (int i = 0; i < 100 ; i++) {
            User user = new User();
            user.setFirstname(faker.name().firstName());
            user.setLastname(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setPassword(passwordEncoder.encode("motdepasse123"));
            user.setAdmin(false);
            user.setActive(faker.bool().bool());
            userRepository.save(user);
        }

        // Employees
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setFirstname(faker.name().firstName());
            employee.setLastname(faker.name().lastName());
            employee.setEmail(faker.internet().emailAddress());
            employee.setPhone(faker.phoneNumber().cellPhone());
            employee.setPassword(passwordEncoder.encode("motdepasse123"));
            employee.setAdmin(false);
            employee.setActive(faker.bool().bool());
            employeeRepository.save(employee);
        }

        // Admin
        User admin = new User();
        admin.setFirstname("Jean");
        admin.setLastname("Dupont");
        admin.setEmail("jean.dupont@mail.com");
        admin.setPassword(passwordEncoder.encode("motdepasse123"));
        admin.setAdmin(true);
        admin.setActive(true);
        userRepository.save(admin);
    }

    private SubscriptionKind createKind(String name, int days, double price) {
        SubscriptionKind kind = new SubscriptionKind();
        kind.setName(name);
        kind.setDurationDays(days);
        kind.setPrice(price);
        return kind;
    }

    private TicketKind createTicketKind(String name, double price) {
        TicketKind kind = new TicketKind();
        kind.setName(name);
        kind.setPrice(price);
        return kind;
    }
}
