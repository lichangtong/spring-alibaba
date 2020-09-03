package com.demo.alibaba.service;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("balanceServiceFallback")
public class BalanceServiceFallback implements BalanceService {
    @Override
    public String getBalance(Integer id) {
        User user= new User("李四","18513781024","北京");
        return JSON.toJSONString(user);
    }
}
