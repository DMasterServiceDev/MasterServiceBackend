package com.example.AccountService.Services;

import com.example.AccountService.Dto.ExeptDto;
import com.example.AccountService.Dto.PatternDto;
import com.example.AccountService.Entities.DayOffExept;
import com.example.AccountService.Entities.DayOnExept;
import com.example.AccountService.Entities.Pattern;

import java.util.List;
import java.util.Optional;

public interface TimetableService {
    List<PatternDto> findAllByMasterId(Long masterId);
    List<PatternDto> findAllByMasterIdAndMonthAndYear(Long masterId, int month, int year);
    List<ExeptDto> findAllDayOffExept(Long masterId);
    List<ExeptDto> findAllDayOnExept(Long masterId);
    Pattern addPattern(Long masterId, PatternDto patternDto);
    List<Pattern> addListPatterns(Long masterId, List<PatternDto> patterns);
    List<ExeptDto> addListExeptions(Long masterId, List<ExeptDto> exeptions, boolean type);// 0 - offExept, 1 - onExept
    List<ExeptDto> separate(List<ExeptDto> exeptions);
    List<ExeptDto> getAllDayOffExeptInMonth(Long masterId,int year,int month);
    List<ExeptDto> getAllDayOnExeptInMonth(Long masterId,int year,int month);
}
