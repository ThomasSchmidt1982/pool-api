package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.AccessRequest;
import fr.schmidt.poolapi.dto.response.AccessResponse;
import fr.schmidt.poolapi.service.AccessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access")
@RequiredArgsConstructor
public class AccessController {

    //injection de dépendance
    private final AccessService accessService;

    @GetMapping
    public List<AccessResponse> findAll(){
        return accessService.findAll();
    }

    @PostMapping("/entry/{userId}")
    public AccessResponse entry(@PathVariable Long userId, @Valid @RequestBody AccessRequest request){
        return accessService.entry(userId, request);
    }

    @PostMapping("/exit/{userId}")
    public AccessResponse exit(@PathVariable Long userId, @Valid @RequestBody AccessRequest request){
        return accessService.exit(userId, request);
    }

}
