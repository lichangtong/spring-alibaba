package com.demo.alibaba.controller;

import com.demo.alibaba.feign.ConsumerService;
import com.demo.alibaba.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pay")
public class UserController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping(value = "/user")
    public String getUser(@RequestBody @Valid User user) {
        return consumerService.queryUser(user);
    }
}
