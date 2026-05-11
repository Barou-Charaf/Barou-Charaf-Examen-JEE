package ma.enset.control.dtos.responses;

import java.util.List;

public record ClientResponse(
        Long id,
        String name,
        String email,
        List<Long> contractIds
) {}
