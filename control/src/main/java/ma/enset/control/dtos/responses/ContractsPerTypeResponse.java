package ma.enset.control.dtos.responses;

public record ContractsPerTypeResponse(
        String type,
        long count
) {}
