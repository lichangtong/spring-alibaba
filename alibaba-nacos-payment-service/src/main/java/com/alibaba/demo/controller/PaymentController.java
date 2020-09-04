package com.alibaba.demo.controller;


import com.alibaba.demo.feign.ConsumerService;
import com.alibaba.fastjson.JSON;
import com.demo.alibaba.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PaymentController {

    @Autowired
    private ConsumerService consumerService;
    @PostMapping(value = "/payment")
    public String getPayment(@RequestBody @Valid User user) {
        log.info("前端请求参数：{}", JSON.toJSONString(user));
       /* Balance  balance = new Balance(100L,2L,2222222L,"你好，这是个测试");
        return JSON.toJSONString(balance);*/

       return consumerService.getUser(new User("上官婉儿","18888888888","大唐"));
    }
}
