package com.demo.alibaba.controller;


import com.alibaba.fastjson.JSON;
import com.demo.alibaba.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/provider")
public class HelloProvider {

//    @Autowired
//    DemoConfig demoConfig;


    @PostMapping("/hello")
    public String helloProvider(MultipartFile file) {
        return "helloProvider = = = fffff -- file";
    }

    @RequestMapping("/balance")
    public String getBalance() {
        return JSON.toJSONString(new User());

    }

}
