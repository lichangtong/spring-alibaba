package com.demo.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", fallback = BalanceServiceFallback.class)
public interface BalanceService {
    @PostMapping( value = "/pay/payment")
    String getBalance(@RequestParam("id") Integer id);
}
