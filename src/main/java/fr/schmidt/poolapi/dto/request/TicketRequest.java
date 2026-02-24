package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TicketRequest(
        @NotNull LocalDateTime usingDate,
        @NotNull Long ticketKindId
) {
}
