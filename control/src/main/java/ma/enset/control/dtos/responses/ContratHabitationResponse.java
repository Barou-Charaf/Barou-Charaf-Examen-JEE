package ma.enset.control.dtos.responses;

import ma.enset.control.enums.StatusContrat;
import ma.enset.control.enums.TypeLogement;
import java.time.LocalDateTime;

public record ContratHabitationResponse(
        Long id,
        LocalDateTime dateSouscription,
        LocalDateTime datevalidation,
        Double montantCotisation,
        int dureeContract,
        Double tauxCouverture,
        StatusContrat status,
        String clientName,
        Long clientId,
        TypeLogement typeLogement,
        String adresseLogement,
        Double superficie
) {}
