package com.alibaba.demo.controller;

import com.alibaba.demo.entity.User;
import com.alibaba.demo.feign.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@EnableAutoConfiguration
public class PaymentController {

    @Autowired
    private ConsumerService consumerService;
    @PostMapping(value = "/payment")
    public String getPayment() {
       /* Balance  balance = new Balance(100L,2L,2222222L,"你好，这是个测试");
        return JSON.toJSONString(balance);*/

       return consumerService.getUser(new User("上官婉儿","18888888888","大唐"));
    }
}
