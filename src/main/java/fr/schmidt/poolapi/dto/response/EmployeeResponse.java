package fr.schmidt.poolapi.dto.response;

public record EmployeeResponse(
        Long id,
        String lastname,
        String firstname,
        String email,
        String phone,
        Boolean isAdmin,
        Boolean isActive
) {
}
