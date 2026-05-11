package ma.enset.control.services;

import ma.enset.control.dtos.requests.LoginRequest;
import ma.enset.control.dtos.requests.RegisterRequest;
import ma.enset.control.dtos.responses.AuthResponse;

public interface AuthenticationService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(String refreshToken);
    AuthResponse me(String username);
}
