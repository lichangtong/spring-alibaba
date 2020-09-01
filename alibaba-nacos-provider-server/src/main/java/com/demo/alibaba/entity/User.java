package com.demo.alibaba.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class User {
    private String uName;
    private String age;
    public void speak(){
        JSON.toJSONString(new User());
    }
}
