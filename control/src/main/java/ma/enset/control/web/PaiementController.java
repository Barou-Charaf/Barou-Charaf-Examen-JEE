package ma.enset.control.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.PaiementRequest;
import ma.enset.control.dtos.responses.PaiementResponse;
import ma.enset.control.enums.TypePaiement;
import ma.enset.control.services.PaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    @GetMapping
    public ResponseEntity<List<PaiementResponse>> findAll() {
        return ResponseEntity.ok(paiementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaiementResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaiementResponse> create(@Valid @RequestBody PaiementRequest request) {
        return new ResponseEntity<>(paiementService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaiementResponse> update(@PathVariable Long id, @Valid @RequestBody PaiementRequest request) {
        return ResponseEntity.ok(paiementService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paiementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<PaiementResponse>> findByContractId(@PathVariable Long contractId) {
        return ResponseEntity.ok(paiementService.findByContratId(contractId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PaiementResponse>> findByType(@PathVariable TypePaiement type) {
        return ResponseEntity.ok(paiementService.findByType(type));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaiementResponse>> findByDateRange(
            @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(paiementService.findByDateRange(start, end));
    }
}
