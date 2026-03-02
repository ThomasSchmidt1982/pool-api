package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.SubscriptionRequest;
import fr.schmidt.poolapi.dto.response.SubscriptionResponse;
import fr.schmidt.poolapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    // injection de dépendance
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> findByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(subscriptionService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> create(@PathVariable Long userId, @Valid@RequestBody SubscriptionRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.create(userId, request));
    }

}
