package com.demo.alibaba.controller;

import com.demo.alibaba.service.impl.HelloService;
import com.demo.alibaba.entity.User;
import com.demo.alibaba.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: SentinelController
 * @Description: sentinel dashboard
 * @Author: lichangtong
 * @Date: 2020-09-07 17:04
 */

@RestController
@RequestMapping("/sentinel")
public class SentinelController {
    @Autowired
    HelloService helloService;
    @PostMapping("/hello")
    public ApiResult helloSentinel(@Valid @RequestBody User user) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setMessage("请求成功 = "+helloService.sayHello());
        apiResult.setData(user);
        return apiResult;
    }



}
