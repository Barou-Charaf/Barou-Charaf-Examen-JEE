package ma.enset.control.dtos.responses;

public record ContractsStatusResponse(
        String status,
        long count
) {}
