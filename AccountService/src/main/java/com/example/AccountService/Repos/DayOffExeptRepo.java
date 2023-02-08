package com.example.AccountService.Repos;

import com.example.AccountService.Entities.DayOffExept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOffExeptRepo extends JpaRepository<DayOffExept,Long> {
    List<DayOffExept> findAllByMasterId(Long masterId);
}
