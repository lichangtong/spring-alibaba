package com.demo.alibaba.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: HelloService
 * @Description: helloService
 * @Author: lichangtong
 * @Date: 2020-09-09 14:31
 */

@Service
public class HelloService {
    @SentinelResource(value = "sayHello",fallback = "helloHandler")
    public String sayHello(){
        return "你好这个被限流了";
    }
    public static String  helloHandler(BlockException ex) {
        return "系统开始限流了1111";
    }
}
