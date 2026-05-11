package ma.enset.control.services;

import ma.enset.control.dtos.requests.PaiementRequest;
import ma.enset.control.dtos.responses.PaiementResponse;
import ma.enset.control.enums.TypePaiement;

import java.time.LocalDateTime;
import java.util.List;

public interface PaiementService {
    List<PaiementResponse> findAll();
    PaiementResponse findById(Long id);
    PaiementResponse create(PaiementRequest request);
    PaiementResponse update(Long id, PaiementRequest request);
    void delete(Long id);
    List<PaiementResponse> findByContratId(Long contratId);
    List<PaiementResponse> findByType(TypePaiement type);
    List<PaiementResponse> findByDateRange(LocalDateTime start, LocalDateTime end);
}
