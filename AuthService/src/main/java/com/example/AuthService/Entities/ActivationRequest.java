package com.example.AuthService.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class ActivationRequest {
    private String name;
    private String description;
    private Date start;
    private Date finish;
}

