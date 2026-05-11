package ma.enset.control.mappers;

import ma.enset.control.dtos.requests.ContratAutomobileRequest;
import ma.enset.control.dtos.requests.ContratHabitationRequest;
import ma.enset.control.dtos.requests.ContratSanteRequest;
import ma.enset.control.dtos.responses.ContratAutomobileResponse;
import ma.enset.control.dtos.responses.ContratHabitationResponse;
import ma.enset.control.dtos.responses.ContratResponse;
import ma.enset.control.dtos.responses.ContratSanteResponse;
import ma.enset.control.entities.*;
import ma.enset.control.enums.StatusContrat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, StatusContrat.class})
public interface ContratMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paiments", ignore = true)
    @Mapping(target = "status", expression = "java(StatusContrat.EN_COURS)")
    @Mapping(target = "dateSouscription", expression = "java(request.dateSouscription() != null ? request.dateSouscription() : LocalDateTime.now())")
    @Mapping(target = "datevalidation", ignore = true)
    @Mapping(target = "client", ignore = true)
    ContratAutomobile toAutoEntity(ContratAutomobileRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paiments", ignore = true)
    @Mapping(target = "status", expression = "java(StatusContrat.EN_COURS)")
    @Mapping(target = "dateSouscription", expression = "java(request.dateSouscription() != null ? request.dateSouscription() : LocalDateTime.now())")
    @Mapping(target = "datevalidation", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "adreselogement", source = "adresseLogement")
    ContratHabitation toHomeEntity(ContratHabitationRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paiments", ignore = true)
    @Mapping(target = "status", expression = "java(StatusContrat.EN_COURS)")
    @Mapping(target = "dateSouscription", expression = "java(request.dateSouscription() != null ? request.dateSouscription() : LocalDateTime.now())")
    @Mapping(target = "datevalidation", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "niveauCouvertur", source = "niveauCouverture")
    ContratSante toHealthEntity(ContratSanteRequest request);

    @Mapping(target = "clientName", expression = "java(contrat.getClient() != null ? contrat.getClient().getName() : null)")
    @Mapping(target = "clientId", expression = "java(contrat.getClient() != null ? contrat.getClient().getId() : null)")
    @Mapping(target = "type", expression = "java(getContratType(contrat))")
    ContratResponse toResponse(Contrat contrat);

    @Mapping(target = "clientName", expression = "java(contrat.getClient() != null ? contrat.getClient().getName() : null)")
    @Mapping(target = "clientId", expression = "java(contrat.getClient() != null ? contrat.getClient().getId() : null)")
    ContratAutomobileResponse toAutoResponse(ContratAutomobile contrat);

    @Mapping(target = "clientName", expression = "java(contrat.getClient() != null ? contrat.getClient().getName() : null)")
    @Mapping(target = "clientId", expression = "java(contrat.getClient() != null ? contrat.getClient().getId() : null)")
    @Mapping(target = "adresseLogement", source = "adreselogement")
    ContratHabitationResponse toHomeResponse(ContratHabitation contrat);

    @Mapping(target = "clientName", expression = "java(contrat.getClient() != null ? contrat.getClient().getName() : null)")
    @Mapping(target = "clientId", expression = "java(contrat.getClient() != null ? contrat.getClient().getId() : null)")
    @Mapping(target = "niveauCouverture", source = "niveauCouvertur")
    ContratSanteResponse toHealthResponse(ContratSante contrat);

    default String getContratType(Contrat contrat) {
        if (contrat instanceof ContratAutomobile) return "AUTO";
        if (contrat instanceof ContratHabitation) return "HOME";
        if (contrat instanceof ContratSante) return "HEALTH";
        return "UNKNOWN";
    }
}
