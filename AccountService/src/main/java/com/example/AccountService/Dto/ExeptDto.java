package com.example.AccountService.Dto;

import com.example.AccountService.Entities.DayOffExept;
import com.example.AccountService.Entities.DayOnExept;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeptDto {
    private int startY;
    private int startM;
    private int startD;
    private int finishY;
    private int finishM;
    private int finishD;
//    private boolean type; // 0 - offExept, 1 - onExept

    public ExeptDto(DayOnExept exept){
        this.startY = exept.getYear();
        this.startM = exept.getMonth();
        this.startD = exept.getStart();
        this.finishY = exept.getYear();
        this.finishM = exept.getMonth();
        this.finishD = exept.getFinish();
    }
    public ExeptDto(DayOffExept exept){
        this.startY = exept.getYear();
        this.startM = exept.getMonth();
        this.startD = exept.getStart();
        this.finishY = exept.getYear();
        this.finishM = exept.getMonth();
        this.finishD = exept.getFinish();
    }
    @JsonIgnore
    public void setStartData(ExeptDto exept){
        this.setStartY(exept.getStartY());
        this.setStartM(exept.getStartM());
        this.setStartD(exept.getStartD());
    }
    @JsonIgnore
    public void setFinishData(ExeptDto exept){
        this.setFinishD(exept.getFinishD());
        this.setFinishM(exept.getFinishM());
        this.setFinishY(exept.getFinishY());
    }

    @JsonIgnore
    public int getMaxDay(int year,int month){
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.set(year,month-1,1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    @JsonIgnore
    public ExeptDto getStartSeparateByYearData(){
        if(this.getStartY() != this.getFinishY()){
            ExeptDto start = new ExeptDto();
            start.setStartData(this);
            start.setFinishY(this.getStartY());
            start.setFinishM(12);
            start.setFinishD(this.getMaxDay(start.getStartY(),start.getFinishM()));
            return start;
        }
        else return null;
    }
    @JsonIgnore
    public ExeptDto getFinishSeparateByYearData(){
        if(this.getStartY() != this.getFinishY()){
            ExeptDto finish = new ExeptDto();
            finish.setFinishData(this);
            finish.setStartY(this.getFinishY());
            finish.setStartM(1);
            finish.setStartD(1);
            return finish;
        }
        else return null;
    }
    @JsonIgnore
    public ExeptDto getStartSeparateByMonthData(){
        if(this.getStartM() != this.getFinishM()){
            ExeptDto start = new ExeptDto();
            start.setStartData(this);
            start.setFinishY(start.getStartY());
            start.setFinishM(start.getStartM());
            start.setFinishD(this.getMaxDay(start.getStartY(),start.getStartM()));
            return start;
        }
        else return null;
    }
    @JsonIgnore
    public ExeptDto getFinishSeparateByMonthData(){
        if(this.getStartM() != this.getFinishM()){
            ExeptDto finish = new ExeptDto();
            finish.setFinishData(this);
            finish.setStartY(finish.getFinishY());
            finish.setStartM(finish.getFinishM());
            finish.setStartD(1);
            return finish;
        }
        else return null;
    }
}
