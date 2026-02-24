package fr.schmidt.poolapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank String lastname,
        @NotBlank String firstname,
        @NotBlank @Email String email,
        @NotBlank @Size(min=8) String password,
        @NotNull Boolean isAdmin,
        @NotNull Boolean isActive
) {
}
