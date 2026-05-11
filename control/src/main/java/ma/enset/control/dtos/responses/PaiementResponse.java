package ma.enset.control.dtos.responses;

import ma.enset.control.enums.TypePaiement;
import java.time.LocalDateTime;

public record PaiementResponse(
        Long id,
        LocalDateTime datePaiement,
        Double montant,
        TypePaiement type,
        Long contratId,
        String contratType
) {}
