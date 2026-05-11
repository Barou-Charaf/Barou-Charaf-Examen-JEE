package ma.enset.control.dtos.responses;

import ma.enset.control.enums.Role;

public record AuthResponse(
        String token,
        String refreshToken,
        Long id,
        String username,
        String email,
        Role role
) {}
