package fr.schmidt.poolapi.dto.response;

import java.time.LocalDate;

public record TicketResponse(
        Long id,
        LocalDate purchaseDate,
        LocalDate usingDate,
        TicketKindResponse ticketKind
) {
}
