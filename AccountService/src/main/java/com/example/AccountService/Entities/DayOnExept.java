package com.example.AccountService.Entities;

import com.example.AccountService.Dto.ExeptDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "DayOnExept")
@NoArgsConstructor
@AllArgsConstructor
public class DayOnExept {
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
    public DayOnExept(ExeptDto exeptDto,Long masterId){
        this.masterId = masterId;
        this.year = exeptDto.getStartY();
        this.month = exeptDto.getStartM();
        this.start = exeptDto.getStartD();
        this.finish = exeptDto.getFinishD();
    }
}
