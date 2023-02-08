package com.example.AccountService.Services;

import com.example.AccountService.Dto.ExeptDto;
import com.example.AccountService.Dto.PatternDto;
import com.example.AccountService.Entities.DayOffExept;
import com.example.AccountService.Entities.DayOnExept;
import com.example.AccountService.Entities.Pattern;
import com.example.AccountService.Repos.DayOffExeptRepo;
import com.example.AccountService.Repos.DayOnExeptRepo;
import com.example.AccountService.Repos.PatternRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimetableServiceImpl implements TimetableService{
    private final PatternRepo patternRepo;
    private final DayOnExeptRepo dayOnExeptRepo;
    private final DayOffExeptRepo dayOffExeptRepo;

    @Autowired
    TimetableServiceImpl(PatternRepo patternRepo,
                         DayOnExeptRepo dayOnExeptRepo,
                         DayOffExeptRepo dayOffExeptRepo){
        this.patternRepo = patternRepo;
        this.dayOnExeptRepo = dayOnExeptRepo;
        this.dayOffExeptRepo = dayOffExeptRepo;
    }
    @Override
    public List<PatternDto> findAllByMasterId(Long masterId) {
        List<Pattern> list = patternRepo.findAllByMasterId(masterId);
        List<PatternDto> answer = new ArrayList<>();
        for (Pattern pattern:list) {
            answer.add(new PatternDto(pattern));
        }
        return answer;
    }
    @Override
    public List<ExeptDto> findAllDayOffExept(Long masterId) {
        List<DayOffExept> list = dayOffExeptRepo.findAllByMasterId(masterId);
        List<ExeptDto> answer = new ArrayList<>();
        for (DayOffExept exept:list) {
            answer.add(new ExeptDto(exept));
        }
        return answer;
    }
    @Override
    public List<ExeptDto> findAllDayOnExept(Long masterId) {
        List<DayOnExept> list = dayOnExeptRepo.findAllByMasterId(masterId);
        List<ExeptDto> answer = new ArrayList<>();
        for (DayOnExept exept:list) {
            answer.add(new ExeptDto(exept));
        }
        return answer;
    }
    @Override
    public Pattern addPattern(Long masterId, PatternDto patternDto) {
        Pattern pattern = new Pattern(patternDto,masterId);
        return patternRepo.save(pattern);
    }
    @Override
    public DayOffExept addDayOffExept(Long masterId, ExeptDto exeptDto) {
        DayOffExept exept = new DayOffExept(exeptDto,masterId);
        return dayOffExeptRepo.save(exept);
    }
    @Override
    public DayOnExept addDayOnExept(Long masterId, ExeptDto exeptDto) {
        DayOnExept exept = new DayOnExept(exeptDto,masterId);
        return dayOnExeptRepo.save(exept);
    }
}
