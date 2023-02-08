package com.example.AccountService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegDto {
    private String login;
    private String name;
    private String description;
    private Date start;
    private Date finish;
}