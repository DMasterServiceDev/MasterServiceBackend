package com.example.AccountService.Dto;

import com.example.AccountService.Entities.DayOffExept;
import com.example.AccountService.Entities.DayOnExept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeptDto {
    private int startY;
    private int startM;
    private int startD;
    private int finishY;
    private int finishM;
    private int finishD;

    public ExeptDto(DayOnExept exept){
        this.startY = exept.getStartY();
        this.startM = exept.getStartM();
        this.startD = exept.getStartD();
        this.finishY = exept.getFinishY();
        this.finishM = exept.getFinishM();
        this.finishD = exept.getFinishD();
    }
    public ExeptDto(DayOffExept exept){
        this.startY = exept.getStartY();
        this.startM = exept.getStartM();
        this.startD = exept.getStartD();
        this.finishY = exept.getFinishY();
        this.finishM = exept.getFinishM();
        this.finishD = exept.getFinishD();
    }
}
