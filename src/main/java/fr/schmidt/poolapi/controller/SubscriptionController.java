package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.SubscriptionRequest;
import fr.schmidt.poolapi.dto.response.SubscriptionResponse;
import fr.schmidt.poolapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    // injection de dépendance
    private final SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionResponse> findByUserId(@PathVariable Long userId){
        return subscriptionService.findByUserId(userId);
    }

    @PostMapping
    public SubscriptionResponse create(@PathVariable Long userId, @Valid@RequestBody SubscriptionRequest request){
        return subscriptionService.create(userId, request);
    }

}
