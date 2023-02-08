package com.example.AccountService.Services;

import com.example.AccountService.Entities.Account;

public interface AccService {

    Account save(Account acc);
    Account findByLogin(String login);
}
