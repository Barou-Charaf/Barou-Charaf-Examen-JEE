package ma.enset.control.mappers;

import ma.enset.control.dtos.responses.UserResponse;
import ma.enset.control.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);
}
