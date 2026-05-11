package ma.enset.control.dtos.requests;

import jakarta.validation.constraints.NotNull;
import ma.enset.control.enums.TypePaiement;
import java.time.LocalDateTime;

public record PaiementRequest(
        LocalDateTime datePaiement,
        @NotNull Double montant,
        @NotNull TypePaiement type,
        @NotNull Long contratId
) {}
