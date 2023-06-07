package com.mark.client.clientservice.adminauth;

import com.mark.client.clientservice.administrator.Administrator;
import com.mark.client.clientservice.administrator.AdministratorRepository;
import com.mark.client.clientservice.administrator.Role;
import com.mark.client.clientservice.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminAuthService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(AdminRegisterRequest request) {
        Optional<Administrator> administratorOptional = administratorRepository.findByEmail(request.getEmail());

        if (administratorOptional.isEmpty()) {
            var admin = Administrator.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();
            administratorRepository.save(admin);
        }
        return null;
    }
    public AdminAuthResponse authenticate(AdminAuthRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        var admin = administratorRepository.findByEmail(email).orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        return AdminAuthResponse.builder().token(jwtToken).build();

    }
}
