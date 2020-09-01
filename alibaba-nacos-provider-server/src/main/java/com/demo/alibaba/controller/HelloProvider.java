package com.demo.alibaba.controller;


import com.alibaba.fastjson.JSON;
import com.demo.alibaba.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
@RefreshScope
public class HelloProvider {

    @Value("${demo.namespace}")
    private String namespace;
    @Autowired
    BalanceService balanceService;

    @RequestMapping("/hello")
    public String helloProvider() {
        return "helloProvider = = =" + namespace;
    }
    @RequestMapping("/balance")
    public String getBalance() {
        return JSON.toJSONString(balanceService.getBalance(100));

    }

}
