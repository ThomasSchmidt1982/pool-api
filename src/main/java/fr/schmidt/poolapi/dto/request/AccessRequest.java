package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccessRequest(
        @NotNull Long poolId
) {
}
