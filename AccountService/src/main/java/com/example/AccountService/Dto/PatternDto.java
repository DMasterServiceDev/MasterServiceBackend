package com.example.AccountService.Dto;

import com.example.AccountService.Entities.Pattern;
import lombok.Data;

@Data
public class PatternDto {

    private int year;
    private int month;
    private int weekday;

    public PatternDto(Pattern pattern){
        this.year = pattern.getYear();
        this.month = pattern.getMonth();
        this.weekday = pattern.getWeekday();
    }
}
