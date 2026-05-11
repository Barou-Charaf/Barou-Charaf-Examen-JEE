package ma.enset.control.services;

import ma.enset.control.dtos.requests.UserRequest;
import ma.enset.control.dtos.responses.UserResponse;
import ma.enset.control.enums.Role;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse create(UserRequest request);
    UserResponse updateRole(Long id, Role role);
    void delete(Long id);
}
