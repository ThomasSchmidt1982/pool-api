package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.AccessRequest;
import fr.schmidt.poolapi.dto.response.AccessResponse;
import fr.schmidt.poolapi.service.AccessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access")
@RequiredArgsConstructor
public class AccessController {

    //injection de dépendance
    private final AccessService accessService;

    @GetMapping
    public ResponseEntity<List<AccessResponse>> findAll(){
        return ResponseEntity.ok(accessService.findAll());
    }

    @PostMapping("/entry/{userId}")
    public ResponseEntity<AccessResponse> entry(@PathVariable Long userId, @Valid @RequestBody AccessRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(accessService.entry(userId, request));
    }

    @PostMapping("/exit/{userId}")
    public ResponseEntity<AccessResponse> exit(@PathVariable Long userId, @Valid @RequestBody AccessRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(accessService.exit(userId, request));
    }

}
