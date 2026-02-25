package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.SubscriptionKindRequest;
import fr.schmidt.poolapi.dto.response.SubscriptionKindResponse;
import fr.schmidt.poolapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions/kinds")
@RequiredArgsConstructor
public class SubscriptionKindController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionKindResponse> findAllKinds() {
        return subscriptionService.findAllKinds();
    }

    @PostMapping
    public SubscriptionKindResponse create(@Valid @RequestBody SubscriptionKindRequest request) {
        return subscriptionService.createKind(request);
    }
}
