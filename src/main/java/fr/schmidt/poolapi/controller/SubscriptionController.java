package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.SubscriptionRequest;
import fr.schmidt.poolapi.dto.response.SubscriptionResponse;
import fr.schmidt.poolapi.model.entity.Person;
import fr.schmidt.poolapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    // injection de dépendance
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> findByUserId(@AuthenticationPrincipal Person person){
        return ResponseEntity.ok(subscriptionService.findByUserId(person.getId()));
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> create(@AuthenticationPrincipal Person person, @Valid@RequestBody SubscriptionRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.create(person.getId(), request));
    }

}
