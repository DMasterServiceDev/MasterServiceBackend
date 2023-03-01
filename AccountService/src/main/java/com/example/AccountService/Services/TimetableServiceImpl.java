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
    public List<PatternDto> findAllByMasterIdAndMonthAndYear(Long masterId, int month, int year) {
        List<Pattern> list = patternRepo.findAllByMasterIdAndMonthAndYear(masterId,month,year);
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
//            answer.add(new ExeptDto(exept));
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
    public List<Pattern> addListPatterns(Long masterId, List<PatternDto> patterns) {
        List<Pattern> answer = new ArrayList<>();
        for (PatternDto pattern : patterns) {
            answer.add(this.addPattern(masterId,pattern));
        }
        return answer;
    }
    @Override
    public List<ExeptDto> addListExeptions(Long masterId, List<ExeptDto> exeptions, boolean type) {
        List<ExeptDto> separated = this.separate(exeptions);
        for(ExeptDto exept:separated){
            if(type == false){ // adding off exeptions
                DayOffExept curExept = new DayOffExept(exept,masterId);
                System.out.println(exept);
                List<DayOnExept> oldOnExeptions = dayOnExeptRepo.findAllByMasterIdInInterval(masterId,exept.getStartY(),exept.getStartM(),exept.getStartD(),exept.getFinishD());
                if(oldOnExeptions.size()!=0){
                    for(DayOnExept temp:oldOnExeptions){
                        if(temp.getStart() < exept.getStartD() && temp.getFinish() > exept.getFinishD()){
                            DayOnExept left = new DayOnExept(masterId,exept.getStartY(),exept.getStartM(),temp.getStart(),exept.getStartD()-1);
                            DayOnExept right = new DayOnExept(masterId,exept.getStartY(),exept.getStartM(),exept.getFinishD()+1,temp.getFinish());
                            dayOnExeptRepo.save(left);
                            dayOnExeptRepo.save(right);
                            dayOnExeptRepo.delete(temp);
                        }
                        else if(temp.getStart() >= exept.getStartD() && temp.getFinish() <= exept.getFinishD()) {
                            dayOnExeptRepo.delete(temp);
                        }
                        else if(temp.getStart() < exept.getStartD() && temp.getFinish() <= exept.getFinishD()){
                            DayOnExept left = new DayOnExept(masterId,exept.getStartY(),exept.getStartM(), temp.getStart(), exept.getStartD()-1);
                            dayOnExeptRepo.save(left);
                            dayOnExeptRepo.delete(temp);
                        }
                        else if(temp.getStart() >= exept.getStartD() && temp.getFinish() > exept.getFinishD()){
                            DayOnExept right = new DayOnExept(masterId,exept.getStartY(),exept.getStartM(),exept.getFinishD()+1,temp.getFinish());
                            dayOnExeptRepo.save(right);
                            dayOnExeptRepo.delete(temp);
                        }
                    }
                }
                List<DayOffExept> oldOffExeptions = dayOffExeptRepo.findAllByMasterIdInInterval(masterId,exept.getStartY(),exept.getStartM(),exept.getStartD(),exept.getFinishD());
                for (DayOffExept temp:oldOffExeptions) {
                    if(temp.getStart() < curExept.getStart()) curExept.setStart(temp.getStart());
                    if(temp.getFinish() > curExept.getFinish()) curExept.setFinish(temp.getFinish());
                    dayOffExeptRepo.delete(temp);
                }
                dayOffExeptRepo.save(curExept);
            }
            else{ // adding on exeptions
                DayOnExept curExept = new DayOnExept(exept,masterId);
                List<DayOffExept> oldOffExeptions = dayOffExeptRepo.findAllByMasterIdInInterval(masterId,exept.getStartY(),exept.getStartM(),exept.getStartD(),exept.getFinishD());
                if(oldOffExeptions.size()!=0){
                    for(DayOffExept temp:oldOffExeptions){
                        if(temp.getStart() < exept.getStartD() && temp.getFinish() > exept.getFinishD()){
                            DayOffExept left = new DayOffExept(masterId,exept.getStartY(),exept.getStartM(),temp.getStart(),exept.getStartD()-1);
                            DayOffExept right = new DayOffExept(masterId,exept.getStartY(),exept.getStartM(),exept.getFinishD()+1,temp.getFinish());
                            dayOffExeptRepo.save(left);
                            dayOffExeptRepo.save(right);
                            dayOffExeptRepo.delete(temp);
                        }
                        else if(temp.getStart() >= exept.getStartD() && temp.getFinish() <= exept.getFinishD()) {
                            dayOffExeptRepo.delete(temp);
                        }
                        else if(temp.getStart() < exept.getStartD() && temp.getFinish() <= exept.getFinishD()){
                            DayOffExept left = new DayOffExept(masterId,exept.getStartY(),exept.getStartM(), temp.getStart(), exept.getStartD()-1);
                            dayOffExeptRepo.save(left);
                            dayOffExeptRepo.delete(temp);
                        }
                        else if(temp.getStart() >= exept.getStartD() && temp.getFinish() > exept.getFinishD()){
                            DayOffExept right = new DayOffExept(masterId,exept.getStartY(),exept.getStartM(),exept.getFinishD()+1,temp.getFinish());
                            dayOffExeptRepo.save(right);
                            dayOffExeptRepo.delete(temp);
                        }
                    }
                }
                List<DayOnExept> oldOnExeptions = dayOnExeptRepo.findAllByMasterIdInInterval(masterId,exept.getStartY(),exept.getStartM(),exept.getStartD(),exept.getFinishD());
                for (DayOnExept temp:oldOnExeptions) {
                    if(temp.getStart() < curExept.getStart()) curExept.setStart(temp.getStart());
                    if(temp.getFinish() > curExept.getFinish()) curExept.setFinish(temp.getFinish());
                    dayOnExeptRepo.delete(temp);
                }
                dayOnExeptRepo.save(curExept);
            }
        }
        return exeptions;
    }
    @Override
    public List<ExeptDto> separate(List<ExeptDto> exeptions) {
        List<ExeptDto> separated = new ArrayList<>();
        for (ExeptDto exept : exeptions) {
            if(exept.getStartM()!=exept.getFinishM()){
                separated.add(exept.getStartSeparateByMonthData());
                separated.add(exept.getFinishSeparateByMonthData());
            }
            else separated.add(exept);
        }
        return separated;
    }
    @Override
    public List<ExeptDto> getAllDayOffExeptInMonth(Long masterId, int year, int month) {
        List<DayOffExept> exeptions = dayOffExeptRepo.findAllByMasterIdAndYearAndMonth(masterId, year, month);
        List<ExeptDto> answer = new ArrayList<>();
        for(DayOffExept exept:exeptions) answer.add(new ExeptDto(exept));
        System.out.println(dayOffExeptRepo.findAllByMasterIdInInterval(masterId,year,month,13,20));
        return answer;
    }

    @Override
    public List<ExeptDto> getAllDayOnExeptInMonth(Long masterId, int year, int month) {
        List<DayOnExept> exeptions = dayOnExeptRepo.findAllByMasterIdAndYearAndMonth(masterId, year, month);
        List<ExeptDto> answer = new ArrayList<>();
        for(DayOnExept exept:exeptions) answer.add(new ExeptDto(exept));
        return answer;
    }
}
