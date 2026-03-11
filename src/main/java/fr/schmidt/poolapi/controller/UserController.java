package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.ChangePasswordRequest;
import fr.schmidt.poolapi.dto.request.UserRequest;
import fr.schmidt.poolapi.dto.response.UserResponse;
import fr.schmidt.poolapi.model.entity.Person;
import fr.schmidt.poolapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //injections de dépendance
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findbyId(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> search(@RequestParam String q) {
        return ResponseEntity.ok(userService.search(q));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(userService.update(id, request));
    }

    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal Person person, @Valid @RequestBody ChangePasswordRequest request){
        userService.changePassword(person.getId(), request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id){
        userService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
