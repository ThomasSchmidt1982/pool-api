package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotNull;

public record SubscriptionRequest(
        @NotNull Long subscriptionKindId
) {
}
