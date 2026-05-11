package ma.enset.control.mappers;

import ma.enset.control.dtos.requests.ClientRequest;
import ma.enset.control.dtos.responses.ClientResponse;
import ma.enset.control.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrats", ignore = true)
    Client toEntity(ClientRequest request);

    @Mapping(target = "contractIds", expression = "java(mapContractIds(client))")
    ClientResponse toResponse(Client client);

    default List<Long> mapContractIds(Client client) {
        if (client.getContrats() == null) return Collections.emptyList();
        return client.getContrats().stream().map(c -> c.getId()).toList();
    }
}
