package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotNull;

public record PoolRequest(
        @NotNull Integer maxCapacity
) {
}
