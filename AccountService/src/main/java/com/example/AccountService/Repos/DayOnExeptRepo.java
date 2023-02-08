package com.example.AccountService.Repos;

import com.example.AccountService.Entities.DayOnExept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOnExeptRepo extends JpaRepository<DayOnExept,Long> {
    List<DayOnExept> findAllByMasterId(Long masterId);
}
