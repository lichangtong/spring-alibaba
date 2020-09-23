package com.demo.alibaba.service;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserPayServiceStrategyFactory
 * @Description: 工厂模式获取用户支付策略
 * @Author: lichangtong
 * @Date: 2020-09-23 10:20
 */

public class UserPayServiceStrategyFactory {
    private static Map<String, IUserPayService> services = new ConcurrentHashMap<String, IUserPayService>();

    public static IUserPayService getUserPayService(String userType) {
        return services.get(userType);
    }

    public static void register(String userType, IUserPayService userPayService) {
        Assert.notNull(userType, "userType can't be null");
        services.put(userType, userPayService);
    }
}
