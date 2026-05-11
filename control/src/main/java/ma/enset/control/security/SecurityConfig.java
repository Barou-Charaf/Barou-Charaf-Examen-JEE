package ma.enset.control.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**","/swagger**/**").permitAll()

                        // Client endpoints
                        .requestMatchers(HttpMethod.GET, "/api/clients/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/clients").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/clients/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/clients/**").hasRole("ADMIN")

                        // Contract endpoints
                        .requestMatchers(HttpMethod.GET, "/api/contracts/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/contracts").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contracts/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contracts/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/contracts/*/validate").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contracts/*/cancel").hasAnyRole("EMPLOYE", "ADMIN")

                        // Auto contracts
                        .requestMatchers(HttpMethod.GET, "/api/auto-contracts/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/auto-contracts").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")

                        // Home contracts
                        .requestMatchers(HttpMethod.GET, "/api/home-contracts/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/home-contracts").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")

                        // Health contracts
                        .requestMatchers(HttpMethod.GET, "/api/health-contracts/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/health-contracts").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")

                        // Payment endpoints
                        .requestMatchers(HttpMethod.GET, "/api/payments/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/payments").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/payments/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/payments/**").hasRole("ADMIN")

                        // User management
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // Dashboard
                        .requestMatchers("/api/dashboard/**").hasAnyRole("EMPLOYE", "ADMIN")

                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
