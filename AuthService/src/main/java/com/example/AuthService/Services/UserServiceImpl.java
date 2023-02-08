package com.example.AuthService.Services;

import com.example.AuthService.Entities.User;
import com.example.AuthService.Repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login).orElse(null);
    }
    @Override
    public User save(User user) {
        return userRepo.save(user);
    }
}

