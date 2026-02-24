package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.UserRequest;
import fr.schmidt.poolapi.dto.response.UserResponse;
import fr.schmidt.poolapi.model.entity.User;
import fr.schmidt.poolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    // injection de dépendance
    private final UserRepository userRepository;

    // GET /users
    public List<UserResponse> findAll() {
        return userRepository.findAll()                     // List<User> depuis la BDD
                .stream()                                   // convertit la liste en Stream pour pouvoir chaîner
                .map(user -> toResponse(user))         // transforme chaque User en UserResponse
                .toList();                                   // reconvertit en List<UserResponse>
    }

    // GET /users/:id
    public UserResponse findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return toResponse(user);
    }

    // POST /users
    public UserResponse create(UserRequest userRequest){
        User user = new User();
        user.setLastname(userRequest.lastname());
        user.setFirstname(userRequest.firstname());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password()); // TODO à hasher plus tard
        user.setAdmin(userRequest.isAdmin());
        user.setActive(userRequest.isActive());
        // sauvegarde en BDD et retourne la réponse
        return toResponse(userRepository.save(user));
    }

    // PUT /users/:id
    public UserResponse update(Long id, UserRequest userRequest){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastname(userRequest.lastname());
        user.setFirstname(userRequest.firstname());
        user.setEmail(userRequest.email());
        user.setAdmin(userRequest.isAdmin());
        user.setActive(userRequest.isActive());
        // Sauvegarde les modifs et retourne la réponse
        return toResponse(userRepository.save(user));
    }

    // DELETE /users/:id (soft delete)
    public void desactivate(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }

    // Mapping entity -> DTO
    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getLastname(),
                user.getFirstname(),
                user.getEmail(),
                user.getPhone(),
                user.isAdmin(),
                user.isActive()
        );
    }
}
