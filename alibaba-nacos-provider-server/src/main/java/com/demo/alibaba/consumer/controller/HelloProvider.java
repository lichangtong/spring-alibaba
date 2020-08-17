package com.demo.alibaba.consumer.controller;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class HelloProvider {

    @NacosValue(value = "${demo.namespace}")
    private String namespace;

    @RequestMapping("/hello")
    public String helloProvider() {
        return "helloProvider = = =" + namespace;
    }
}
