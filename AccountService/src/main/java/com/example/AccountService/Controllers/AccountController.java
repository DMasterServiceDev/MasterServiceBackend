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
                                               @RequestBody PatternDto patternDto) {
        Account acc = accService.findByLogin(login);
        Response response = new Response();
        if (acc == null) {
            response.setAnswer("Something went wrong");
        } else {
            timetableService.addPattern(acc.getId(), patternDto);
            response.setAnswer("Pattern successfully add");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/pattern")
    @Operation(
            description = "Getting list of patterns"
    )
    public List<PatternDto> patterns(@RequestHeader("username")String login){
        Account acc = accService.findByLogin(login);
        if(acc == null) return null;
        else return timetableService.findAllByMasterId(acc.getId());
    }
    @PostMapping("/exept/off")
    @Operation(
            description = "Adding day off exeption"
    )
    public ResponseEntity<Response> addOffExeption(@RequestHeader("username") String login,
                                                   @RequestBody ExeptDto exeption){
        Account acc = accService.findByLogin(login);
        Response response = new Response();
        if (acc == null) {
            response.setAnswer("Something went wrong");
        } else {
            timetableService.addDayOffExept(acc.getId(), exeption);
            response.setAnswer("Pattern successfully add");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/exept/off")
    @Operation(
            description = "Getting list of day off exeptions"
    )
    public List<ExeptDto> daysOff(@RequestHeader("username")String login){
        Account acc = accService.findByLogin(login);
        if(acc == null) return null;
        else return timetableService.findAllDayOffExept(acc.getId());
    }
    @PostMapping("/exept/on")
    @Operation(
            description = "Adding day on exeption"
    )
    public ResponseEntity<Response> addOnExeption(@RequestHeader("username") String login,
                                                  @RequestBody ExeptDto exeption){
        Account acc = accService.findByLogin(login);
        Response response = new Response();
        if (acc == null) {
            response.setAnswer("Something went wrong");
        } else {
            timetableService.addDayOnExept(acc.getId(), exeption);
            response.setAnswer("Pattern successfully add");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/exept/on")
    @Operation(
            description = "Getting list of day on exeptions"
    )
    public List<ExeptDto> daysOn(@RequestHeader("username")String login){
        Account acc = accService.findByLogin(login);
        if(acc == null) return null;
        else return timetableService.findAllDayOnExept(acc.getId());
    }


}
