package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.SubscriptionKindRequest;
import fr.schmidt.poolapi.dto.request.SubscriptionRequest;
import fr.schmidt.poolapi.dto.response.SubscriptionKindResponse;
import fr.schmidt.poolapi.dto.response.SubscriptionResponse;
import fr.schmidt.poolapi.exception.ResourceNotFoundException;
import fr.schmidt.poolapi.model.entity.Subscription;
import fr.schmidt.poolapi.model.entity.SubscriptionKind;
import fr.schmidt.poolapi.model.entity.User;
import fr.schmidt.poolapi.repository.SubscriptionKindRepository;
import fr.schmidt.poolapi.repository.SubscriptionRepository;
import fr.schmidt.poolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    // injection de dépendance
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionKindRepository subscriptionKindRepository;
    private final UserRepository userRepository;

    // GET /subscriptions/kinds
    // Retourne le catalogue des types d'abonnement disponibles
    public List<SubscriptionKindResponse> findAllKinds(){
     return subscriptionKindRepository.findAll()
             .stream()
             .map(kind-> toKindResponse(kind))
             .toList();
    }

    // POST /subscriptions/kinds
    // Crée un nouveau type d'abonnement (ADMIN only)
    public SubscriptionKindResponse createKind(SubscriptionKindRequest request){
        SubscriptionKind kind = new SubscriptionKind();
        kind.setName(request.name());
        kind.setPrice(request.price());
        kind.setDurationDays(request.durationDays());
        return toKindResponse(subscriptionKindRepository.save(kind));
    }

    // POST /users/:id/subscriptions
    // Vend un abonnement à un user
    public SubscriptionResponse create(Long id, SubscriptionRequest request){
        // 1. Vérifie que user existe
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not Found"));
        // 2. Vérifie que le type d'abonnement existe
        SubscriptionKind kind = subscriptionKindRepository.findById(request.subscriptionKindId())
                .orElseThrow(()->new ResourceNotFoundException("SubscriptionKind not found"));
        // 3. crée l'abonnement
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionKind(kind);
        subscription.setPurchaseDate(LocalDate.now());
        subscription.setExpirationDate(LocalDate.now().plusDays(kind.getDurationDays()));
        // sauvegarde en BDD et retourne la réponse
        return toResponse(subscriptionRepository.save(subscription));
    }

    // GET /users/:id/subscriptions
    // Retourne tous les abonnements d'un user
    public List<SubscriptionResponse> findByUserId(Long id) {
        return subscriptionRepository.findByUserId(id)
                .stream()
                .map(subscription -> toResponse(subscription))
                .toList();
    }


    // Mapping entity -> DTO
    private SubscriptionResponse toResponse(Subscription subscription){
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getPurchaseDate(),
                subscription.getExpirationDate(),
                toKindResponse(subscription.getSubscriptionKind())
        );
    }
    private SubscriptionKindResponse toKindResponse(SubscriptionKind kind){
        return new SubscriptionKindResponse(
                kind.getId(),
                kind.getName(),
                kind.getPrice(),
                kind.getDurationDays()
        );
    }
}
