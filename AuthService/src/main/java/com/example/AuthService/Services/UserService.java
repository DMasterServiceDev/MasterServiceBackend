package com.example.AuthService.Services;

import com.example.AuthService.Entities.User;

public interface UserService {
    User findById(Long id);
    User findByLogin(String login);
    User save(User user);
}
