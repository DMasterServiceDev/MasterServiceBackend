package com.example.AccountService.mq;

import com.example.AccountService.Dto.AccountRegDto;
import com.example.AccountService.Entities.Account;
import com.example.AccountService.Services.AccService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitListener {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AccService accService;

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "[Master]RegAccount")
    public void newAcc(String msg) {
        try{
            AccountRegDto accDto = mapper.readValue(msg, AccountRegDto.class);
            Account acc = new Account(accDto);
            accService.save(acc);
            System.out.println(acc);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

}