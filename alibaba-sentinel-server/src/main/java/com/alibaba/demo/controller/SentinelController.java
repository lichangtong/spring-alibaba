package com.alibaba.demo.controller;

import com.demo.alibaba.entity.User;
import com.demo.alibaba.result.ApiResult;
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
    @PostMapping("hello")
    public ApiResult helloSentinel(@Valid @RequestBody User user) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setMessage("请求成功");
        apiResult.setData(user);
        return apiResult;
    }
}
