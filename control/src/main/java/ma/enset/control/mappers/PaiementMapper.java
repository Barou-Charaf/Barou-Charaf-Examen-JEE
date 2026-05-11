package ma.enset.control.mappers;

import ma.enset.control.dtos.requests.PaiementRequest;
import ma.enset.control.dtos.responses.PaiementResponse;
import ma.enset.control.entities.Paiement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    @Mapping(target = "datePaiement", expression = "java(request.datePaiement() != null ? request.datePaiement() : java.time.LocalDateTime.now())")
    Paiement toEntity(PaiementRequest request);

    @Mapping(target = "contratId", expression = "java(paiement.getContrat() != null ? paiement.getContrat().getId() : null)")
    @Mapping(target = "contratType", expression = "java(paiement.getContrat() != null ? getContratType(paiement.getContrat()) : null)")
    PaiementResponse toResponse(Paiement paiement);

    default String getContratType(Object contrat) {
        if (contrat instanceof ma.enset.control.entities.ContratAutomobile) return "AUTO";
        if (contrat instanceof ma.enset.control.entities.ContratHabitation) return "HOME";
        if (contrat instanceof ma.enset.control.entities.ContratSante) return "HEALTH";
        return "UNKNOWN";
    }
}
