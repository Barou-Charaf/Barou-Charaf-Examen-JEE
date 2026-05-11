package ma.enset.control.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.PaiementRequest;
import ma.enset.control.dtos.responses.PaiementResponse;
import ma.enset.control.entities.Contrat;
import ma.enset.control.entities.Paiement;
import ma.enset.control.enums.TypePaiement;
import ma.enset.control.exception.ResourceNotFoundException;
import ma.enset.control.mappers.PaiementMapper;
import ma.enset.control.repositories.ContratRepository;
import ma.enset.control.repositories.PaiementRepository;
import ma.enset.control.services.PaiementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public List<PaiementResponse> findAll() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::toResponse)
                .toList();
    }

    @Override
    public PaiementResponse findById(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
        return paiementMapper.toResponse(paiement);
    }

    @Override
    @Transactional
    public PaiementResponse create(PaiementRequest request) {
        Contrat contrat = contratRepository.findById(request.contratId())
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", request.contratId()));

        Paiement paiement = paiementMapper.toEntity(request);
        paiement.setContrat(contrat);
        paiement = paiementRepository.save(paiement);
        return paiementMapper.toResponse(paiement);
    }

    @Override
    @Transactional
    public PaiementResponse update(Long id, PaiementRequest request) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));

        paiement.setMontant(request.montant());
        paiement.setType(request.type());
        if (request.datePaiement() != null) paiement.setDatePaiement(request.datePaiement());

        if (request.contratId() != null && !request.contratId().equals(paiement.getContrat().getId())) {
            Contrat contrat = contratRepository.findById(request.contratId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contrat", request.contratId()));
            paiement.setContrat(contrat);
        }

        paiement = paiementRepository.save(paiement);
        return paiementMapper.toResponse(paiement);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paiement", id);
        }
        paiementRepository.deleteById(id);
    }

    @Override
    public List<PaiementResponse> findByContratId(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::toResponse)
                .toList();
    }

    @Override
    public List<PaiementResponse> findByType(TypePaiement type) {
        return paiementRepository.findByType(type).stream()
                .map(paiementMapper::toResponse)
                .toList();
    }

    @Override
    public List<PaiementResponse> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return paiementRepository.findByDatePaiementBetween(start, end).stream()
                .map(paiementMapper::toResponse)
                .toList();
    }
}
