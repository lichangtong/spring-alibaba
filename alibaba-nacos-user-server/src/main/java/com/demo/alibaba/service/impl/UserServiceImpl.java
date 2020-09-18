package com.demo.alibaba.service.impl;

import com.alibaba.demo.config.RedissonClintUtil;
import com.demo.alibaba.entity.User;
import com.demo.alibaba.service.IUserService;
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
    RedissonClintUtil redissonClintUtil;

    @Override
    public String queryUserById(User user) {
        System.out.println(redissonClintUtil.toString());
//        RBucket rBucket = redissonClient.getBucket("");
//        if (rBucket.isExists()) {
//            return rBucket.get().toString();
//        } else {
//            rBucket.set("lichangtong");
//        }
//        return rBucket.get().toString();
        return null;
    }
}
