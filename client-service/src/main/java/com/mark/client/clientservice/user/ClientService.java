package com.mark.client.clientservice.user;

import com.mark.client.clientservice.account.Account;
import com.mark.client.clientservice.account.AccountRepo;
import com.mark.client.clientservice.authentication.LoginRequest;
import com.mark.client.clientservice.authentication.LoginResponse;
import com.mark.client.clientservice.authentication.RegisterRequest;
import com.mark.client.clientservice.config.jwt.JwtService;
import com.mark.client.clientservice.redis.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AccountRepo accountRepo;
    private final RedisPublisher redisPublisher;

    public Client register(RegisterRequest request) {
        Optional<Client> clientOptional = clientRepo.findByEmail(request.getEmail());
        if (clientOptional.isEmpty()) {
            var user = Client.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.CLIENT)
                    .build();
            clientRepo.save(user);

            // after registering a user , a default amount of 1000 is given
            Account account = Account.builder()
                    .clientId(user.getClientId())
                    .balance(1000.00)
                    .build();
            accountRepo.save(account);

            user.setAccountId(account.getId());

            // save the user
            var client = clientRepo.save(user);


            ClientDTO clientDTO = ClientDTO.builder()
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .role(Role.CLIENT)
                    .accountId(client.getAccountId())
                    .clientId(client.getClientId())
                    .dateRegistered(LocalDateTime.now())
                    .build();
            redisPublisher.publishMessage(clientDTO.toString());
            return user;
        }
        // I have to refactor to return the right status code if email already exists.
        throw new RuntimeException("This email has been registered.") ;
    }
    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = clientRepo.findByEmail(request.getEmail()).orElseThrow(); // throw something
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder().token(jwtToken).build();
    }
}
