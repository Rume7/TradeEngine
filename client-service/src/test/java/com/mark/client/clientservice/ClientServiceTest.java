//package com.mark.client.clientService;
//import com.mark.client.clientService.account.Account;
//import com.mark.client.clientService.account.AccountRepo;
//import com.mark.client.clientService.authentication.LoginRequest;
//import com.mark.client.clientService.authentication.LoginResponse;
//import com.mark.client.clientService.authentication.RegisterRequest;
//import com.mark.client.clientService.config.jwt.JwtService;
//import com.mark.client.clientService.user.Client;
//import com.mark.client.clientService.user.ClientRepo;
//import com.mark.client.clientService.user.ClientService;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class ClientServiceTest {
//    @Mock
//    private ClientRepo clientRepo;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Mock
//    private JwtService jwtService;
//    @Mock
//    private AuthenticationManager authenticationManager;
//    @Mock
//    private AccountRepo accountRepo;
//    @InjectMocks
//    private ClientService clientService;
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    public void testRegister_SuccessfulRegistration() {
//        // Arrange
//        RegisterRequest registerRequest = new RegisterRequest("mark", "tetteh", "mark@gmail.com", "mark");
//        when(clientRepo.findByEmail(registerRequest.getEmail())).thenReturn(Optional.empty());
//        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
//
//        // Act
//        String result = clientService.register(registerRequest);
//
//        // Assert
//        assertEquals("You have successfully registered with R2M", result);
//        verify(clientRepo, times(2)).save(any(Client.class));
//    }
//
//    @Test
//    public void testRegister_EmailAlreadyExists() {
//        // Arrange
//        RegisterRequest registerRequest = new RegisterRequest("mark", "tetteh", "mark@gmail.com", "mark");
//        when(clientRepo.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(new Client()));
//
//        // Act
//        String result = clientService.register(registerRequest);
//
//        // Assert
//        assertEquals("The email you are trying to register with is associated with another account", result);
//        verify(clientRepo, never()).save(any(Client.class));
//        verify(accountRepo, never()).save(any(Account.class));
//    }
//
//    @Test
//    public void testAuthenticate() {
//        // Arrange
//        LoginRequest loginRequest = new LoginRequest("johndoe@example.com", "password");
//        Client client = new Client();
//        when(clientRepo.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(client));
//        when(jwtService.generateToken(client)).thenReturn("jwtToken");
//
//        // Act
//        LoginResponse response = clientService.authenticate(loginRequest);
//
//        // Assert
//        assertEquals("jwtToken", response.getToken());
//    }
//}
