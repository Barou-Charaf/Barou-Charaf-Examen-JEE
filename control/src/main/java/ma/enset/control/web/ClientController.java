package ma.enset.control.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ClientRequest;
import ma.enset.control.dtos.responses.ClientResponse;
import ma.enset.control.dtos.responses.ContratResponse;
import ma.enset.control.services.ClientService;
import ma.enset.control.services.ContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ContratService contratService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest request) {
        return new ResponseEntity<>(clientService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/contracts")
    public ResponseEntity<List<ContratResponse>> getClientContracts(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findByClientId(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(clientService.search(keyword));
    }
}
