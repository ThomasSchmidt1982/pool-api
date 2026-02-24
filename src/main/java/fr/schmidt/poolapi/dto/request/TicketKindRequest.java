package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotNull;

public record TicketKindRequest(
        @NotNull String name,
        @NotNull Double price
) {
}
