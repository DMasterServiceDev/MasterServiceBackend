package com.example.AccountService.Entities;

import com.example.AccountService.Dto.AccountRegDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "Accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "start")
    private Date start;
    @Column(name = "finish")
    private Date finish;

  public Account(AccountRegDto acc){
      this.login = acc.getLogin();
      this.name = acc.getName();
      this.description = acc.getDescription();
      this.start = acc.getStart();
      this.finish = acc.getFinish();
      start.setMonth(0);
      start.setDate(0);
      finish.setMonth(0);
      finish.setDate(0);
  }
}
