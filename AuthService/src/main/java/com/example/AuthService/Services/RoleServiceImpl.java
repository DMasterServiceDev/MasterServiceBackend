package com.example.AuthService.Services;

import com.example.AuthService.Entities.Role;
import com.example.AuthService.Repos.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    RoleServiceImpl(RoleRepo roleRepo){
        this.roleRepo = roleRepo;
    }

    public Role findById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }
}
