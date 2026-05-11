package ma.enset.control.services;

import ma.enset.control.dtos.requests.ClientRequest;
import ma.enset.control.dtos.responses.ClientResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> findAll();
    ClientResponse findById(Long id);
    ClientResponse create(ClientRequest request);
    ClientResponse update(Long id, ClientRequest request);
    void delete(Long id);
    List<ClientResponse> search(String keyword);
    List<ClientResponse> findContractsByClientId(Long clientId);
}
