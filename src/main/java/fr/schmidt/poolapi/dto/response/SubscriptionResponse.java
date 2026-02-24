package fr.schmidt.poolapi.dto.response;

import java.time.LocalDate;

public record SubscriptionResponse(
        Long id,
        LocalDate purchaseDate,
        LocalDate expirationDate,
        SubscriptionKindResponse subscriptionKind
) {
}
