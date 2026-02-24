package fr.schmidt.poolapi.dto.response;

public record SubscriptionKindResponse(
        Long id,
        String name,
        Double price,
        Integer durationDays
) {
}
