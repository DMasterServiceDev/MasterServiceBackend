package com.example.AccountService.Services;

import com.example.AccountService.Entities.Account;
import com.example.AccountService.Repos.AccRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccServiceImpl implements AccService{

    @PersistenceContext
    private EntityManager em;

    private final AccRepo accRepo;

    @Autowired
    AccServiceImpl(AccRepo accRepo){
        this.accRepo = accRepo;
    }
    @Override
    public Account save(Account acc) { return accRepo.save(acc); }
    @Override
    public Account findByLogin(String login) {
        return accRepo.findByLogin(login).orElse(null);
    }

}
