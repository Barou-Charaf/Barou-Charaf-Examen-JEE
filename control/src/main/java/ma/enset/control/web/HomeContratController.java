package ma.enset.control.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.control.dtos.requests.ContratHabitationRequest;
import ma.enset.control.dtos.responses.ContratHabitationResponse;
import ma.enset.control.services.ContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home-contracts")
@RequiredArgsConstructor
public class HomeContratController {

    private final ContratService contratService;

    @GetMapping
    public ResponseEntity<List<ContratHabitationResponse>> findAll() {
        return ResponseEntity.ok(contratService.findAllHome());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratHabitationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findHomeById(id));
    }

    @PostMapping
    public ResponseEntity<ContratHabitationResponse> create(@Valid @RequestBody ContratHabitationRequest request) {
        var resp = contratService.createHome(request);
        return new ResponseEntity<>(contratService.findHomeById(resp.id()), HttpStatus.CREATED);
    }
}
