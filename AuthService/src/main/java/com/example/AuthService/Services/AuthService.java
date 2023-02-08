package com.example.AuthService.Services;


import com.example.AuthService.Entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwt;
    private final UserService userService;
    private final StatusService statusService;
    private final AmqpTemplate amqpTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AuthService(final JwtUtil jwt,
                        UserService userService,
                       StatusService statusService,
                       AmqpTemplate amqpTemplate) {
        this.jwt = jwt;
        this.userService = userService;
        this.amqpTemplate = amqpTemplate;
        this.statusService = statusService;
    }

    public AuthResponse register(RegistrationRequest registrationRequest) {
        User user = userService.findByLogin(registrationRequest.getLogin());
        if(user != null) return new AuthResponse("Username already exist", "Change your credentials");

        user = new User(registrationRequest);
        user.setPassword(BCrypt.hashpw(registrationRequest.getPassword(), BCrypt.gensalt()));
        user.setStatusId(0L);
        userService.save(user);

        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }
    public AuthResponse login(LoginRequest request){
        User user = userService.findByLogin(request.getLogin());
        if(user == null){
            return new AuthResponse("Invalid data","Change your credentials");
        }
        boolean correct = BCrypt.checkpw(request.getPassword(),user.getPassword());
        if(!correct){
            return new AuthResponse("Invalid data","Change your credentials");
        }
        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }
    public Status changeStatus(Long statusId, Long userId){
        User user = userService.findById(userId);
        user.setStatusId(statusId);
        userService.save(user);
        return statusService.findById(user.getStatusId());
    }
    public AuthResponse activate(Long userId, ActivationRequest activationRequest) throws JsonProcessingException {
        User user = userService.findById(userId);
        Status status = this.changeStatus(1L,userId);
        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");
        if(status.getName().equals("ACTIVE")){
            AccountDto accountDto = new AccountDto(user,activationRequest);
            System.out.println(accountDto);
            amqpTemplate.convertAndSend("[Master]RegAccount",mapper.writeValueAsString(accountDto));
        }
        return new AuthResponse(accessToken, refreshToken);
    }
}
