package com.example.AccountService.Entities;

import com.example.AccountService.Dto.PatternDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Patterns")
@NoArgsConstructor
@AllArgsConstructor
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "master_id")
    private Long masterId;
    @Column(name = "year")
    private int year;
    @Column(name = "month")
    private int month; // 1 - monday, 7 - sunday, 0 - all days
    @Column(name = "weekday")
    private int weekday;

    public Pattern(PatternDto patternDto, Long masterId){
        this.year = patternDto.getYear();
        this.month = patternDto.getMonth();
        this.weekday = patternDto.getWeekday();
        this.masterId = masterId;
    }
}
