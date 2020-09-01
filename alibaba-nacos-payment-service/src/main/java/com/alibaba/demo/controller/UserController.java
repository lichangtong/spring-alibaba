package com.alibaba.demo.controller;

import com.alibaba.demo.entity.User;
import com.alibaba.demo.feign.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pay")
public class UserController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping(value = "/user")
    public String getUser(@RequestBody @Valid User user) {
        return consumerService.getUser(user);
    }
}
