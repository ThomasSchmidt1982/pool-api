package fr.schmidt.poolapi.dto.response;

public record PoolResponse(
        Long id,
        Integer maxCapacity,
        Integer occupancy,
        Integer currentCapacity
) {
}
