package com.demo.alibaba.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: RedissonProperties
 * @Description: RedissonProperties
 * @Author: lichangtong
 * @Date: 2020-09-14 11:05
 */
@Component
@Data
@ToString
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {
    private int timeout = 3000;
    private String address;
    private String password;
    private int connectionPoolSize = 64;
    private int connectionMinimumIdleSize = 10;
    private int slaveConnectionPoolSize = 250;
    private int masterConnectionPoolSize = 250;
    private String[] sentinelAddresses;
    private String masterName;
    private int db;
}
