package ma.enset.control.services;

import ma.enset.control.dtos.requests.ContratAutomobileRequest;
import ma.enset.control.dtos.requests.ContratHabitationRequest;
import ma.enset.control.dtos.requests.ContratSanteRequest;
import ma.enset.control.dtos.responses.ContratAutomobileResponse;
import ma.enset.control.dtos.responses.ContratHabitationResponse;
import ma.enset.control.dtos.responses.ContratResponse;
import ma.enset.control.dtos.responses.ContratSanteResponse;
import ma.enset.control.enums.StatusContrat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ContratService {
    Page<ContratResponse> findAll(Pageable pageable);
    ContratResponse findById(Long id);
    ContratAutomobileResponse createAuto(ContratAutomobileRequest request);
    ContratHabitationResponse createHome(ContratHabitationRequest request);
    ContratSanteResponse createHealth(ContratSanteRequest request);
    ContratResponse update(Long id, ContratAutomobileRequest request);
    ContratResponse update(Long id, ContratHabitationRequest request);
    ContratResponse update(Long id, ContratSanteRequest request);
    void delete(Long id);
    ContratResponse validate(Long id);
    ContratResponse cancel(Long id);
    List<ContratResponse> findByStatus(StatusContrat status);
    List<ContratResponse> findByClientId(Long clientId);
    List<ContratResponse> findByDateRange(LocalDateTime start, LocalDateTime end);
    List<ContratResponse> search(String keyword);
    ContratAutomobileResponse findAutoById(Long id);
    ContratHabitationResponse findHomeById(Long id);
    ContratSanteResponse findHealthById(Long id);
    List<ContratAutomobileResponse> findAllAuto();
    List<ContratHabitationResponse> findAllHome();
    List<ContratSanteResponse> findAllHealth();
    List<ContratResponse> searchAutoByImmatriculation(String immatriculation);
}
