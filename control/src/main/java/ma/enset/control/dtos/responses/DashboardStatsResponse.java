package ma.enset.control.dtos.responses;

public record DashboardStatsResponse(
        long totalClients,
        long totalContracts,
        long totalPayments,
        double totalPaymentsAmount
) {}
