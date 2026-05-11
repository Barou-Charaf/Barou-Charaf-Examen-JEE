package ma.enset.control.web;

import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.responses.ContractsPerTypeResponse;
import ma.enset.control.dtos.responses.ContractsStatusResponse;
import ma.enset.control.dtos.responses.DashboardStatsResponse;
import ma.enset.control.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getStats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }

    @GetMapping("/contracts-per-type")
    public ResponseEntity<List<ContractsPerTypeResponse>> getContractsPerType() {
        return ResponseEntity.ok(dashboardService.getContractsPerType());
    }

    @GetMapping("/payments-total")
    public ResponseEntity<Double> getPaymentsTotal() {
        return ResponseEntity.ok(dashboardService.getPaymentsTotal());
    }

    @GetMapping("/contracts-status")
    public ResponseEntity<List<ContractsStatusResponse>> getContractsStatus() {
        return ResponseEntity.ok(dashboardService.getContractsStatus());
    }
}
