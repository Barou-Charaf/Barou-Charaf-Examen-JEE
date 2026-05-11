package ma.enset.control.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ContratAutomobileRequest;
import ma.enset.control.dtos.responses.ContratAutomobileResponse;
import ma.enset.control.dtos.responses.ContratResponse;
import ma.enset.control.services.ContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auto-contracts")
@RequiredArgsConstructor
public class AutoContratController {

    private final ContratService contratService;

    @GetMapping
    public ResponseEntity<List<ContratAutomobileResponse>> findAll() {
        return ResponseEntity.ok(contratService.findAllAuto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratAutomobileResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findAutoById(id));
    }

    @PostMapping
    public ResponseEntity<ContratAutomobileResponse> create(@Valid @RequestBody ContratAutomobileRequest request) {
        var resp = contratService.createAuto(request);
        return new ResponseEntity<>(contratService.findAutoById(resp.id()), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContratResponse>> searchByImmatriculation(@RequestParam String immatriculation) {
        return ResponseEntity.ok(contratService.searchAutoByImmatriculation(immatriculation));
    }
}
