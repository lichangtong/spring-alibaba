package com.demo.alibaba.controller;


import com.alibaba.fastjson.JSON;
import com.demo.alibaba.config.DemoConfig;
import com.demo.alibaba.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class HelloProvider {

    @Autowired
    DemoConfig demoConfig;


    @RequestMapping("/hello")
    public String helloProvider() {
        return "helloProvider = = =" + demoConfig.getNamespace();
    }

    @RequestMapping("/balance")
    public String getBalance() {
        return JSON.toJSONString(new User());

    }

}
