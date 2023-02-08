package com.example.AccountService.Repos;

import com.example.AccountService.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);
}
