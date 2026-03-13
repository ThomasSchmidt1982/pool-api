package fr.schmidt.poolapi.dto.response;

import java.time.LocalDate;

public record TicketResponse(
        Long id,
        Long userId,
        LocalDate purchaseDate,
        LocalDate usingDate,
        TicketKindResponse ticketKind
) {
}
