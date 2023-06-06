package com.mark.client.clientService.adminauth;


import com.mark.client.clientService.administrator.AdministratorService;
import com.mark.client.clientService.user.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService service;
    private final AdministratorService administratorService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AdminRegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AdminAuthResponse> authenticate(@RequestBody AdminAuthRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/get-all-clients")
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(administratorService.getAllClients());
    }


}

