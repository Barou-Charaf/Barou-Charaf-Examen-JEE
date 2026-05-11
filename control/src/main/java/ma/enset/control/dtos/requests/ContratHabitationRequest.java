package ma.enset.control.dtos.requests;

import jakarta.validation.constraints.*;
import ma.enset.control.enums.TypeLogement;
import java.time.LocalDateTime;

public record ContratHabitationRequest(
        LocalDateTime dateSouscription,
        LocalDateTime datevalidation,
        @NotNull Double montantCotisation,
        @Min(1) int dureeContract,
        @NotNull Double tauxCouverture,
        @NotNull Long clientId,
        @NotNull TypeLogement typeLogement,
        @NotBlank String adresseLogement,
        @NotNull Double superficie
) {}
