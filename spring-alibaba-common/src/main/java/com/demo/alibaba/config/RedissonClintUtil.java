package com.demo.alibaba.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: RedissonClintUtil
 * @Description: clientUtil
 * @Author: lichangtong
 * @Date: 2020-09-14 14:46
 */

@Component
public class RedissonClintUtil {
    @Autowired
    private RedissonProperties properties;

//    @Bean
//    public RedissonClient redissonClient() {
//        System.out.println(properties);
//        return Redisson.create();
//    }

    @Bean
    public RedissonClient redissonClient() {

        System.out.println(properties.toString());
        Config config = new Config();
        /*
         address  = -- host:port
        */
//        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(properties.getAddress());
//        if (StringUtils.isNoneEmpty(properties.getPassword())) {
//            singleServerConfig.setPassword(properties.getPassword());
//        }

//        singleServerConfig.setDatabase(properties.getDb());
//        singleServerConfig.setConnectionPoolSize(properties.getConnectionPoolSize());
//        singleServerConfig.setConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize());
        config.setCodec(new org.redisson.codec.JsonJacksonCodec());
        config.setNettyThreads(4);
        config.useSingleServer()
                .setAddress(properties.getAddress())
                .setDatabase(properties.getDb())
                .setConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize())
                .setConnectionPoolSize(properties.getConnectionPoolSize()).setConnectTimeout(properties.getTimeout());
        if (StringUtils.isNoneEmpty(properties.getPassword())) {
            config.useSingleServer().setPassword(properties.getPassword());
        }
        return Redisson.create(config);
    }
}
