package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TicketRequest(
        LocalDateTime usingDate,
        @NotNull Long ticketKindId
) {
}
