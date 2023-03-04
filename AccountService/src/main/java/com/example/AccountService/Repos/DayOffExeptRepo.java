package com.example.AccountService.Repos;

import com.example.AccountService.Entities.DayOffExept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DayOffExeptRepo extends JpaRepository<DayOffExept,Long> {
    List<DayOffExept> findAllByMasterId(Long masterId);
    List<DayOffExept> findAllByMasterIdAndYearAndMonth(Long masterId,int year,int month);
    @Query("SELECT e FROM DayOffExept e WHERE e.masterId = :masterId AND e.year = :year AND e.month = :month AND (e.finish >= :start OR e.start <= :finish)")
    List<DayOffExept> findAllByMasterIdInInterval(@Param("masterId") Long masterId,
                                                  @Param("year") int year,
                                                  @Param("month") int month,
                                                  @Param("start") int start,
                                                  @Param("finish") int finish);
}
