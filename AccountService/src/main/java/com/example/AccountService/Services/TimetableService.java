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
    List<ExeptDto> findAllDayOffExept(Long masterId);
    List<ExeptDto> findAllDayOnExept(Long masterId);
    Pattern addPattern(Long masterId, PatternDto patternDto);
    DayOffExept addDayOffExept(Long masterId, ExeptDto exeptDto);
    DayOnExept addDayOnExept(Long masterId, ExeptDto exeptDto);
}
