package com.example.AccountService.Controllers;

import com.example.AccountService.Dto.ExeptDto;
import com.example.AccountService.Dto.PatternDto;
import com.example.AccountService.Dto.Response;
import com.example.AccountService.Entities.Account;
import com.example.AccountService.Services.AccService;
import com.example.AccountService.Services.TimetableService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccService accService;
    @Autowired
    private TimetableService timetableService;

    @GetMapping("/test")
    public Date test(){
        Date date = new Date();
        return date;
    }

    @PostMapping("/pattern")
    @Operation(
            description = "Adding day off patter"
    )
    public ResponseEntity<Response> addPattern(@RequestHeader("username") String login,
                                               @RequestBody List<PatternDto> patternDto) {
        Account acc = accService.findByLogin(login);
        Response response = new Response();
        if (acc == null) {
            response.setAnswer("Something went wrong");
        } else {
            timetableService.addListPatterns(acc.getId(), patternDto);
            response.setAnswer("Pattern successfully add");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/pattern")
    @Operation(
            description = "Getting list of patterns"
    )
    public List<PatternDto> patterns(@RequestHeader("username")String login
                                     ,@RequestParam("month") int month
                                     ,@RequestParam("year") int year){
        Account acc = accService.findByLogin(login);
        if(acc == null) return null;
        else return timetableService.findAllByMasterIdAndMonthAndYear(acc.getId(), month, year);
    }
    @PostMapping("/exept/off")
    @Operation(
            description = "Adding on exeptions"
    )
    public ResponseEntity<Response> addOffExeptions(@RequestHeader("username") String login,
                                                    @RequestBody List<ExeptDto> exeptions){
        Account acc = accService.findByLogin(login);
        Response response = new Response();
        if(acc == null) response.setAnswer("Something went wrong");
        else {
            timetableService.addListExeptions(acc.getId(), exeptions,false);
            response.setAnswer("Exeption successfully add");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/exept/off")
    @Operation(
            description = "Get off exeptions in month"
    )
    public List<ExeptDto> getOffExeptions(@RequestHeader("username") String login
                                        ,@RequestParam("month") int month
                                        ,@RequestParam("year") int year){
        Account acc = accService.findByLogin(login);
        if(acc == null) return null;
        else return timetableService.getAllDayOffExeptInMonth(acc.getId(),year,month);
    }

    @PostMapping("/exept/on")
    @Operation(
            description = "Adding on exeptions"
    )
    public ResponseEntity<Response> addOnExeptions(@RequestHeader("username") String login,
                                                   @RequestBody List<ExeptDto> exeptions){
        Account acc = accService.findByLogin(login);
        Response response = new Response();
        if(acc == null) response.setAnswer("Something went wrong");
        else {
            timetableService.addListExeptions(acc.getId(), exeptions,true);
            response.setAnswer("Exeption successfully add");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/exept/on")
    @Operation(
            description = "Get on exeptions in month"
    )
    public List<ExeptDto> getOnExeptions(@RequestHeader("username") String login
                                        ,@RequestParam("month") int month
                                        ,@RequestParam("year") int year){
        Account acc = accService.findByLogin(login);
        if(acc == null) return null;
        else return timetableService.getAllDayOnExeptInMonth(acc.getId(),year,month);
    }
}
