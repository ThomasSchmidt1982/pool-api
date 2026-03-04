package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.UserRequest;
import fr.schmidt.poolapi.dto.response.UserResponse;
import fr.schmidt.poolapi.exception.ResourceNotFoundException;
import fr.schmidt.poolapi.model.entity.User;
import fr.schmidt.poolapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void findById_shouldReturnUser_whenUserExists() {
        // Arrange ( Préparation des données )
        User user = new User();
        user.setId(1L);
        user.setFirstname("Jean");
        user.setLastname("Dupont");
        user.setEmail("jean.dupont@mail.com");
        // indique le comportement du mock
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act ( Appelle la méthode à tester )
        UserResponse response = userService.findById(1L);

        // Assert ( Vérification du résultat )
        assertThat(response.firstname()).isEqualTo("Jean");
        assertThat(response.lastname()).isEqualTo("Dupont");
        assertThat(response.email()).isEqualTo("jean.dupont@mail.com");
    }

    @Test
    void findById_shouldThrowException_whenUserNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> userService.findById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found");

    }

    @Test
    void create_shouldReturnUserResponse_whenUserCreated() {
        // Arrange
        User user = new User();
        UserRequest request = new UserRequest(
                "Dupont",
                "Jean",
                "jean@mail.com",
                "motdepasse123",
                false,
                true
        );
        when(passwordEncoder.encode("motdepasse123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });


        // Act
        UserResponse response = userService.create(request);

        // Assert
        assertThat(response.email()).isEqualTo("jean@mail.com");
        assertThat(response.firstname()).isEqualTo("Jean");
    }

    @Test
    void deactivate_shouldSetUserInactive_whenUserExists() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setActive(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.deactivate(1L);

        // Assert
        assertThat(user.isActive()).isFalse();
    }
}