package com.demo.alibaba.controller;

import com.demo.alibaba.config.RedissonClintUtil;
import com.demo.alibaba.config.RedissonProperties;
import com.demo.alibaba.entity.User;
import com.demo.alibaba.result.ApiResult;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
    //    @Autowired
//    @Qualifier("userService")
//    IUserService userService;

    @Autowired
    private RedissonClintUtil redissonClintUtil;

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping(value = "/query")
    public ApiResult queryUser() {
        ApiResult apiResult = ApiResult.builder().build();
        apiResult.setMessage("SUCCESS");
        apiResult.setCode(200);

//        userService.queryUserById(new User());
        System.out.println(redissonClintUtil.toString());
        RBucket bucket = redissonClient.getBucket("nihao");
        if (bucket.isExists()) {
            bucket.get();
        } else {
            bucket.set("这是一个测试！！！");
        }
        apiResult.setData(new User("lisi", "13213121314", "北京凯旋" + bucket.get()));

        RLock rLock = redissonClient.getLock("lisiiii");
        System.out.println(rLock.isLocked());

        rLock.lock(10, TimeUnit.SECONDS);

        rLock.unlock();
        return apiResult;
    }

    /**
     *
     * @return
     */
    @PostMapping(value = "/query2")
    public ApiResult query2() {
		ApiResult apiResult = ApiResult.builder().build();
        apiResult.setMessage("SUCCESS");
        apiResult.setCode(200);
        return apiResult;
    }

    public String test(RedissonProperties properties) {

        return properties.toString();
    }

}
