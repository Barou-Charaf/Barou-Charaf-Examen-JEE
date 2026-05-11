package ma.enset.control.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.responses.ContractsPerTypeResponse;
import ma.enset.control.dtos.responses.ContractsStatusResponse;
import ma.enset.control.dtos.responses.DashboardStatsResponse;
import ma.enset.control.entities.*;
import ma.enset.control.enums.StatusContrat;
import ma.enset.control.repositories.ClientRepository;
import ma.enset.control.repositories.ContratRepository;
import ma.enset.control.repositories.PaiementRepository;
import ma.enset.control.services.DashboardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ClientRepository clientRepository;
    private final ContratRepository contratRepository;
    private final PaiementRepository paiementRepository;

    @Override
    public DashboardStatsResponse getStats() {
        long totalClients = clientRepository.count();
        long totalContracts = contratRepository.count();
        long totalPayments = paiementRepository.count();
        double totalPaymentsAmount = paiementRepository.findAll().stream()
                .mapToDouble(Paiement::getMontant)
                .sum();
        return new DashboardStatsResponse(totalClients, totalContracts, totalPayments, totalPaymentsAmount);
    }

    @Override
    public List<ContractsPerTypeResponse> getContractsPerType() {
        List<ContractsPerTypeResponse> result = new ArrayList<>();
        List<Contrat> all = contratRepository.findAll();

        long auto = all.stream().filter(c -> c instanceof ContratAutomobile).count();
        long home = all.stream().filter(c -> c instanceof ContratHabitation).count();
        long health = all.stream().filter(c -> c instanceof ContratSante).count();

        result.add(new ContractsPerTypeResponse("AUTO", auto));
        result.add(new ContractsPerTypeResponse("HOME", home));
        result.add(new ContractsPerTypeResponse("HEALTH", health));

        return result;
    }

    @Override
    public double getPaymentsTotal() {
        return paiementRepository.findAll().stream()
                .mapToDouble(Paiement::getMontant)
                .sum();
    }

    @Override
    public List<ContractsStatusResponse> getContractsStatus() {
        List<ContractsStatusResponse> result = new ArrayList<>();
        for (StatusContrat status : StatusContrat.values()) {
            long count = contratRepository.countByStatus(status);
            result.add(new ContractsStatusResponse(status.name(), count));
        }
        return result;
    }
}
