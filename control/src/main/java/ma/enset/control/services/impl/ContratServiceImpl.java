package ma.enset.control.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ContratAutomobileRequest;
import ma.enset.control.dtos.requests.ContratHabitationRequest;
import ma.enset.control.dtos.requests.ContratSanteRequest;
import ma.enset.control.dtos.responses.ContratAutomobileResponse;
import ma.enset.control.dtos.responses.ContratHabitationResponse;
import ma.enset.control.dtos.responses.ContratResponse;
import ma.enset.control.dtos.responses.ContratSanteResponse;
import ma.enset.control.entities.*;
import ma.enset.control.enums.StatusContrat;
import ma.enset.control.exception.BadRequestException;
import ma.enset.control.exception.ResourceNotFoundException;
import ma.enset.control.mappers.ContratMapper;
import ma.enset.control.repositories.ClientRepository;
import ma.enset.control.repositories.ContratRepository;
import ma.enset.control.services.ContratService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements ContratService {

    private final ContratRepository contratRepository;
    private final ClientRepository clientRepository;
    private final ContratMapper contratMapper;

    @Override
    public Page<ContratResponse> findAll(Pageable pageable) {
        return contratRepository.findAll(pageable)
                .map(contratMapper::toResponse);
    }

    @Override
    public ContratResponse findById(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        return contratMapper.toResponse(contrat);
    }

    @Override
    @Transactional
    public ContratAutomobileResponse createAuto(ContratAutomobileRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", request.clientId()));

        ContratAutomobile contrat = contratMapper.toAutoEntity(request);
        contrat.setClient(client);
        contrat = contratRepository.save(contrat);
        return contratMapper.toAutoResponse(contrat);
    }

    @Override
    @Transactional
    public ContratHabitationResponse createHome(ContratHabitationRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", request.clientId()));

        ContratHabitation contrat = contratMapper.toHomeEntity(request);
        contrat.setClient(client);
        contrat = contratRepository.save(contrat);
        return contratMapper.toHomeResponse(contrat);
    }

    @Override
    @Transactional
    public ContratSanteResponse createHealth(ContratSanteRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", request.clientId()));

        ContratSante contrat = contratMapper.toHealthEntity(request);
        contrat.setClient(client);
        contrat = contratRepository.save(contrat);
        return contratMapper.toHealthResponse(contrat);
    }

    @Override
    @Transactional
    public ContratResponse update(Long id, ContratAutomobileRequest request) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (!(contrat instanceof ContratAutomobile auto)) {
            throw new BadRequestException("Contract is not an auto contract");
        }
        auto.setMontantCotisation(request.montantCotisation());
        auto.setDureeContract(request.dureeContract());
        auto.setTauxCouverture(request.tauxCouverture());
        auto.setNumeroImmatriculation(request.numeroImmatriculation());
        auto.setMarqueVehicule(request.marqueVehicule());
        auto.setModeleVehicule(request.modeleVehicule());
        if (request.datevalidation() != null) auto.setDatevalidation(request.datevalidation());
        contrat = contratRepository.save(auto);
        return contratMapper.toResponse(contrat);
    }

    @Override
    @Transactional
    public ContratResponse update(Long id, ContratHabitationRequest request) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (!(contrat instanceof ContratHabitation home)) {
            throw new BadRequestException("Contract is not a home contract");
        }
        home.setMontantCotisation(request.montantCotisation());
        home.setDureeContract(request.dureeContract());
        home.setTauxCouverture(request.tauxCouverture());
        home.setTypeLogement(request.typeLogement());
        home.setAdreselogement(request.adresseLogement());
        home.setSuperficie(request.superficie());
        if (request.datevalidation() != null) home.setDatevalidation(request.datevalidation());
        contrat = contratRepository.save(home);
        return contratMapper.toResponse(contrat);
    }

    @Override
    @Transactional
    public ContratResponse update(Long id, ContratSanteRequest request) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (!(contrat instanceof ContratSante health)) {
            throw new BadRequestException("Contract is not a health contract");
        }
        health.setMontantCotisation(request.montantCotisation());
        health.setDureeContract(request.dureeContract());
        health.setTauxCouverture(request.tauxCouverture());
        health.setNiveauCouvertur(request.niveauCouverture());
        health.setNombrePersonnesCouvertes(request.nombrePersonnesCouvertes());
        if (request.datevalidation() != null) health.setDatevalidation(request.datevalidation());
        contrat = contratRepository.save(health);
        return contratMapper.toResponse(contrat);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ContratResponse validate(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        contrat.setStatus(StatusContrat.VALID);
        contrat.setDatevalidation(LocalDateTime.now());
        contrat = contratRepository.save(contrat);
        return contratMapper.toResponse(contrat);
    }

    @Override
    @Transactional
    public ContratResponse cancel(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        contrat.setStatus(StatusContrat.RESILIE);
        contrat = contratRepository.save(contrat);
        return contratMapper.toResponse(contrat);
    }

    @Override
    public List<ContratResponse> findByStatus(StatusContrat status) {
        return contratRepository.findByStatus(status).stream()
                .map(contratMapper::toResponse)
                .toList();
    }

    @Override
    public List<ContratResponse> findByClientId(Long clientId) {
        return contratRepository.findByClientId(clientId).stream()
                .map(contratMapper::toResponse)
                .toList();
    }

    @Override
    public List<ContratResponse> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return contratRepository.findByDateSouscriptionBetween(start, end).stream()
                .map(contratMapper::toResponse)
                .toList();
    }

    @Override
    public List<ContratResponse> search(String keyword) {
        return contratRepository.searchByKeyword(keyword).stream()
                .map(contratMapper::toResponse)
                .toList();
    }

    @Override
    public ContratAutomobileResponse findAutoById(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (!(contrat instanceof ContratAutomobile auto)) {
            throw new BadRequestException("Contract is not an auto contract");
        }
        return contratMapper.toAutoResponse(auto);
    }

    @Override
    public ContratHabitationResponse findHomeById(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (!(contrat instanceof ContratHabitation home)) {
            throw new BadRequestException("Contract is not a home contract");
        }
        return contratMapper.toHomeResponse(home);
    }

    @Override
    public ContratSanteResponse findHealthById(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        if (!(contrat instanceof ContratSante health)) {
            throw new BadRequestException("Contract is not a health contract");
        }
        return contratMapper.toHealthResponse(health);
    }

    @Override
    public List<ContratAutomobileResponse> findAllAuto() {
        return contratRepository.findAll().stream()
                .filter(c -> c instanceof ContratAutomobile)
                .map(c -> contratMapper.toAutoResponse((ContratAutomobile) c))
                .toList();
    }

    @Override
    public List<ContratHabitationResponse> findAllHome() {
        return contratRepository.findAll().stream()
                .filter(c -> c instanceof ContratHabitation)
                .map(c -> contratMapper.toHomeResponse((ContratHabitation) c))
                .toList();
    }

    @Override
    public List<ContratSanteResponse> findAllHealth() {
        return contratRepository.findAll().stream()
                .filter(c -> c instanceof ContratSante)
                .map(c -> contratMapper.toHealthResponse((ContratSante) c))
                .toList();
    }

    @Override
    public List<ContratResponse> searchAutoByImmatriculation(String immatriculation) {
        return contratRepository.searchAutoByImmatriculation(immatriculation).stream()
                .map(contratMapper::toResponse)
                .toList();
    }
}
