package ma.enset.control.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ClientRequest;
import ma.enset.control.dtos.responses.ClientResponse;
import ma.enset.control.entities.Client;
import ma.enset.control.exception.ResourceNotFoundException;
import ma.enset.control.mappers.ClientMapper;
import ma.enset.control.repositories.ClientRepository;
import ma.enset.control.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    public ClientResponse findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        return clientMapper.toResponse(client);
    }

    @Override
    @Transactional
    public ClientResponse create(ClientRequest request) {
        Client client = clientMapper.toEntity(request);
        client = clientRepository.save(client);
        return clientMapper.toResponse(client);
    }

    @Override
    @Transactional
    public ClientResponse update(Long id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        client.setName(request.name());
        client.setEmail(request.email());
        client = clientRepository.save(client);
        return clientMapper.toResponse(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientResponse> search(String keyword) {
        return clientRepository.searchByKeyword(keyword).stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    public List<ClientResponse> findContractsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        return List.of(clientMapper.toResponse(client));
    }
}
