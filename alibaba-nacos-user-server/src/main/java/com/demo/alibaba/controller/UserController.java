package com.demo.alibaba.controller;

import com.demo.alibaba.entity.User;
import com.demo.alibaba.result.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserController
 * @Description: 用户信息查询
 * @Author: lichangtong
 * @Date: 2020-09-07 11:58
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping(value = "/query")
    public ApiResult queryUser() {
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage("SUCCESS");
        apiResult.setCode(200);
        apiResult.setData(new User("lisi", "13213121314", "北京凯旋"));
        return apiResult;
    }
}
