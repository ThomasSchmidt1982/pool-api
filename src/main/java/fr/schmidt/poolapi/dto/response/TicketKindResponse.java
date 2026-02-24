package fr.schmidt.poolapi.dto.response;

public record TicketKindResponse(
        Long id,
        String name,
        Double price
) {
}
