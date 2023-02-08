package com.example.AuthService.Services;

import com.example.AuthService.Entities.Status;
import com.example.AuthService.Repos.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService{

    private final StatusRepo statusRepo;

    StatusServiceImpl(StatusRepo statusRepo){
        this.statusRepo = statusRepo;
    }
    public Status findById(Long id) {
        return statusRepo.findById(id).orElse(null);
    }
}
