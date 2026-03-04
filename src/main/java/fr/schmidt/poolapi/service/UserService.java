package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.ChangePasswordRequest;
import fr.schmidt.poolapi.dto.request.UserRequest;
import fr.schmidt.poolapi.dto.response.UserResponse;
import fr.schmidt.poolapi.exception.BadRequestException;
import fr.schmidt.poolapi.exception.ResourceNotFoundException;
import fr.schmidt.poolapi.model.entity.User;
import fr.schmidt.poolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    // injection de dépendance
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    // POST /users
    public UserResponse create(UserRequest userRequest){
        User user = new User();
        user.setLastname(userRequest.lastname());
        user.setFirstname(userRequest.firstname());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setAdmin(userRequest.isAdmin());
        user.setActive(userRequest.isActive());
        // sauvegarde en BDD et retourne la réponse
        return toResponse(userRepository.save(user));
    }

    // PUT /users/:id
    public UserResponse update(Long id, UserRequest userRequest){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setLastname(userRequest.lastname());
        user.setFirstname(userRequest.firstname());
        user.setEmail(userRequest.email());
        user.setAdmin(userRequest.isAdmin());
        user.setActive(userRequest.isActive());
        // Sauvegarde les modifs et retourne la réponse
        return toResponse(userRepository.save(user));
    }

    // PUT /password/:id
    public void changePassword(Long id, ChangePasswordRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())){
            throw new BadRequestException("Invalid password");
        }
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
    }

    // DELETE /users/:id (soft delete)
    public void deactivate(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
