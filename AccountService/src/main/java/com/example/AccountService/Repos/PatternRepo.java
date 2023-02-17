package com.example.AccountService.Repos;

import com.example.AccountService.Entities.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatternRepo extends JpaRepository<Pattern,Long> {
    List<Pattern> findAllByMasterId(Long masterId);
    List<Pattern> findAllByMasterIdAndMonthAndYear(Long masterId, int month, int year);
}
