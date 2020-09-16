package com.demo.alibaba.service.impl;

import com.demo.alibaba.entity.User;
import com.demo.alibaba.service.IUserService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: lichangtong
 * @Date: 2020-09-14 14:06
 */

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    RedissonClient redissonClient;

    @Override
    public String queryUserById(User user) {
        RBucket rBucket = redissonClient.getBucket("");
        if (rBucket.isExists()) {
            return rBucket.get().toString();
        } else {
            rBucket.set("lichangtong");
        }
        return rBucket.get().toString();
    }
}
