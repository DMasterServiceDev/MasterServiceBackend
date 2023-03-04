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
    @Column(name = "year")
    private int year;
    @Column(name = "month")
    private int month;
    @Column(name = "start")
    private int start;
    @Column(name = "finish")
    private int finish;
    public DayOffExept(ExeptDto exeptDto,Long masterId){
        this.masterId = masterId;
        this.year = exeptDto.getStartY();
        this.month = exeptDto.getStartM();
        this.start = exeptDto.getStartD();
        this.finish = exeptDto.getFinishD();
    }
    public DayOffExept(Long masterId,int year,int month,int start,int finish){
        this.masterId = masterId;
        this.year = year;
        this.month = month;
        this.start = start;
        this.finish = finish;
    }
//    public DayOffExept(Long masterId,int year,int month,int start, int finish){
//        this.masterId = masterId;
//        this.year = year;
//        this.month = month;
//        this.start = start;
//        this.finish = finish;
//    }
}
