package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriptionKindRequest(
        @NotBlank String name,
        @NotNull Integer durationDays,
        @NotNull Double price
) {
}
