package com.example.AuthService.Controllers;

import com.example.AuthService.Entities.ActivationRequest;
import com.example.AuthService.Entities.LoginRequest;
import com.example.AuthService.Entities.RegistrationRequest;
import com.example.AuthService.Entities.AuthResponse;
import com.example.AuthService.Services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private final AuthService authService;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest registrationRequest) throws JsonProcessingException {
        return ResponseEntity.ok(authService.register(registrationRequest));
    }
    @PostMapping("/activate")
    public ResponseEntity<AuthResponse> activate(@RequestHeader("id") Long masterId, @RequestBody ActivationRequest actRequest) throws JsonProcessingException {
        return ResponseEntity.ok(authService.activate(masterId,actRequest));
    }
}
