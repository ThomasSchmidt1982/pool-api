package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.AccessRequest;
import fr.schmidt.poolapi.dto.response.AccessResponse;
import fr.schmidt.poolapi.dto.response.UserResponse;
import fr.schmidt.poolapi.model.entity.Access;
import fr.schmidt.poolapi.model.entity.Pool;
import fr.schmidt.poolapi.model.entity.User;
import fr.schmidt.poolapi.model.enums.InOut;
import fr.schmidt.poolapi.repository.AccessRepository;
import fr.schmidt.poolapi.repository.PoolRepository;
import fr.schmidt.poolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessService {

    // injections de dépendances
    private final PoolRepository poolRepository;
    private final AccessRepository accessRepository;
    private final UserRepository userRepository;

    // GET /access
    // Voir tous les accès piscine
    public List<AccessResponse> findAll(){
        return accessRepository.findAll()
                .stream()
                .map(access->toResponse(access))
                .toList();
    }

    // POST /access/entry
    // Entrer dans la piscine
    public AccessResponse entry(Long userId, AccessRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not Found"));
        Pool pool = poolRepository.findById(request.poolId())
                .orElseThrow(()->new RuntimeException("Pool not found"));
        Access access = new Access();
        access.setUser(user);
        access.setTimestamp(LocalDateTime.now());
        access.setPool(pool);
        access.setInOut(InOut.ENTRY);
        return toResponse(accessRepository.save(access));
    }


    // POST /access/exit
    // Sortir de la piscine
    public AccessResponse exit(Long userId, AccessRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not Found"));
        Pool pool = poolRepository.findById(request.poolId())
                .orElseThrow(()->new RuntimeException("Pool not found"));
        Access access = new Access();
        access.setUser(user);
        access.setTimestamp(LocalDateTime.now());
        access.setPool(pool);
        access.setInOut(InOut.EXIT);
        return toResponse(accessRepository.save(access));
    }

    // Mapping entity -> Dto
    private AccessResponse toResponse(Access access){
        return new AccessResponse(
                access.getId(),
                access.getPool().getId(),
                access.getTimestamp(),
                access.getInOut(),
                toUserResponse(access.getUser())
        );
    }
    private UserResponse toUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getLastname(),
                user.getFirstname(),
                user.getEmail(),
                user.getPhone(),
                user.isActive(),
                user.isAdmin()
        );
    }

}
