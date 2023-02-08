package com.example.AuthService.Repos;

import com.example.AuthService.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepo extends JpaRepository<Status,Long> {
    Optional<Status> findById(Long id);
}
