package ma.enset.control.dtos.requests;

import jakarta.validation.constraints.*;
import ma.enset.control.enums.NiveauCouverture;
import java.time.LocalDateTime;

public record ContratSanteRequest(
        LocalDateTime dateSouscription,
        LocalDateTime datevalidation,
        @NotNull Double montantCotisation,
        @Min(1) int dureeContract,
        @NotNull Double tauxCouverture,
        @NotNull Long clientId,
        @NotNull NiveauCouverture niveauCouverture,
        @Min(1) int nombrePersonnesCouvertes
) {}
