package ma.enset.control.services;

import ma.enset.control.dtos.responses.ContractsPerTypeResponse;
import ma.enset.control.dtos.responses.ContractsStatusResponse;
import ma.enset.control.dtos.responses.DashboardStatsResponse;

import java.util.List;

public interface DashboardService {
    DashboardStatsResponse getStats();
    List<ContractsPerTypeResponse> getContractsPerType();
    double getPaymentsTotal();
    List<ContractsStatusResponse> getContractsStatus();
}
