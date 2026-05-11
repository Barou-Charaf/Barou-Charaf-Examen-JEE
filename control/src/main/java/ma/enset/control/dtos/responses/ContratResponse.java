package ma.enset.control.dtos.responses;

import ma.enset.control.enums.StatusContrat;
import java.time.LocalDateTime;

public record ContratResponse(
        Long id,
        LocalDateTime dateSouscription,
        LocalDateTime datevalidation,
        Double montantCotisation,
        int dureeContract,
        Double tauxCouverture,
        StatusContrat status,
        String clientName,
        Long clientId,
        String type
) {}
