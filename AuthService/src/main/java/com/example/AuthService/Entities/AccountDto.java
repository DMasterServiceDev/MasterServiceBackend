package com.example.AuthService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String login;
    private String name;
    private String description;
    private Date start;
    private Date finish;
    public AccountDto(User user, ActivationRequest activationRequest){
        this.login = user.getLogin();
        this.name = activationRequest.getName();
        this.description = activationRequest.getDescription();
        this.start = activationRequest.getStart();
        this.finish = activationRequest.getFinish();
    }
}
