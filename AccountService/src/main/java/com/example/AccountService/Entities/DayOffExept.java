package com.example.AccountService.Entities;

import com.example.AccountService.Dto.ExeptDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "DayOffExept")
@NoArgsConstructor
@AllArgsConstructor
public class DayOffExept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "master_id")
    private Long masterId;
    @Column(name = "start_year")
    private int startY;
    @Column(name = "start_month")
    private int startM;
    @Column(name = "master_day")
    private int startD;
    @Column(name = "finish_year")
    private int finishY;
    @Column(name = "finish_month")
    private int finishM;
    @Column(name = "finish_day")
    private int finishD;
    public DayOffExept(ExeptDto exeptDto,Long masterId){
        this.masterId = masterId;
        this.startY = exeptDto.getStartY();
        this.startM = exeptDto.getStartM();
        this.startD = exeptDto.getStartD();
        this.finishY = exeptDto.getFinishY();
        this.finishM = exeptDto.getFinishM();
        this.finishD = exeptDto.getFinishD();
    }
}
