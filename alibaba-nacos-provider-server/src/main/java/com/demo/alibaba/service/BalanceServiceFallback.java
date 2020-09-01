package com.demo.alibaba.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("balanceServiceFallback")
public class BalanceServiceFallback implements BalanceService {
    @Override
    public String getBalance(Integer id) {
        /*Balance balance = new Balance(2000L, 2222L, 2222333L, "处理llll");
        return JSON.toJSONString(balance);*/
        return null;
    }
}
