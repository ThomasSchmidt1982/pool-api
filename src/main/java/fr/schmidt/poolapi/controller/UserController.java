package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.UserRequest;
import fr.schmidt.poolapi.dto.response.UserResponse;
import fr.schmidt.poolapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //injections de dépendance
    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findbyId(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request){
        return userService.create(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest request){
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id){
        userService.deactivate(id);
    }
}
