package ma.enset.control.dtos.requests;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ContratAutomobileRequest(
        LocalDateTime dateSouscription,
        LocalDateTime datevalidation,
        @NotNull Double montantCotisation,
        @Min(1) int dureeContract,
        @NotNull Double tauxCouverture,
        @NotNull Long clientId,
        @NotBlank String numeroImmatriculation,
        @NotBlank String marqueVehicule,
        String modeleVehicule
) {}
