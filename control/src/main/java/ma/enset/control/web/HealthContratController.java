package ma.enset.control.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ContratSanteRequest;
import ma.enset.control.dtos.responses.ContratSanteResponse;
import ma.enset.control.services.ContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-contracts")
@RequiredArgsConstructor
public class HealthContratController {

    private final ContratService contratService;

    @GetMapping
    public ResponseEntity<List<ContratSanteResponse>> findAll() {
        return ResponseEntity.ok(contratService.findAllHealth());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratSanteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findHealthById(id));
    }

    @PostMapping
    public ResponseEntity<ContratSanteResponse> create(@Valid @RequestBody ContratSanteRequest request) {
        var resp = contratService.createHealth(request);
        return new ResponseEntity<>(contratService.findHealthById(resp.id()), HttpStatus.CREATED);
    }
}
