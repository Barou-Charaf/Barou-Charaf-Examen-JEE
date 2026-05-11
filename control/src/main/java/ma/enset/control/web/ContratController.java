package ma.enset.control.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ContratAutomobileRequest;
import ma.enset.control.dtos.responses.ContratResponse;
import ma.enset.control.enums.StatusContrat;
import ma.enset.control.services.ContratService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContratController {

    private final ContratService contratService;

    @GetMapping
    public ResponseEntity<Page<ContratResponse>> findAll(
            @PageableDefault(size = 10, sort = "dateSouscription", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(contratService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContratResponse> create(@Valid @RequestBody ContratAutomobileRequest request) {
        return new ResponseEntity<>(contratService.createAuto(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratResponse> update(@PathVariable Long id, @Valid @RequestBody ContratAutomobileRequest request) {
        return ResponseEntity.ok(contratService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contratService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/validate")
    public ResponseEntity<ContratResponse> validate(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.validate(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ContratResponse> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.cancel(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContratResponse>> findByStatus(@PathVariable StatusContrat status) {
        return ResponseEntity.ok(contratService.findByStatus(status));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ContratResponse>> findByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(contratService.findByClientId(clientId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ContratResponse>> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(contratService.findByDateRange(start, end));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContratResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(contratService.search(keyword));
    }
}
