package com.demo.alibaba.controller;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/consumer")
@Slf4j
@RefreshScope
public class ConsumerController {

    @Value(value = "${demo.namespace}")
    private String namespace;
    @PostMapping(value = "/user")
    public String queryUser(@Valid @RequestBody User user) {
        log.info("客户端输入的入参：{}", JSON.toJSONString(user));
        User user1 = new User("wangwu", "18888888888", "北京凯旋城" + namespace);
        return JSON.toJSONString(user1);
    }
}
