package ma.enset.control.dtos.responses;

import ma.enset.control.enums.Role;

public record UserResponse(
        Long id,
        String username,
        String email,
        Role role,
        boolean enabled
) {}
