package com.example.AuthService.Repos;

import com.example.AuthService.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findById(Long id);
}
