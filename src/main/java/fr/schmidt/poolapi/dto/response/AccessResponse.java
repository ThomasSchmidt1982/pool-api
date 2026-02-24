package fr.schmidt.poolapi.dto.response;

import fr.schmidt.poolapi.model.enums.InOut;

import java.time.LocalDateTime;

public record AccessResponse(
        Long id,
        Long poolId,
        LocalDateTime timestamp,
        InOut inOut,
        UserResponse user
) {
}
