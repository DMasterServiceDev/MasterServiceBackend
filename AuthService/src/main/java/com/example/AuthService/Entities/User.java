package com.example.AuthService.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role_id")
    private Long role;
    @Column(name = "status_id")
    private Long statusId;
    public User(RegistrationRequest request){
        this.login = request.getLogin();
        if(request.getRole().equals("ROLE_MASTER")) this.role = 1L;
        else this.role = 2L;
    }
}
