package ma.enset.control.dtos.responses;

import jakarta.validation.constraints.NotNull;
import ma.enset.control.enums.Role;

public record RoleUpdateRequest(
        @NotNull Role role
) {}
