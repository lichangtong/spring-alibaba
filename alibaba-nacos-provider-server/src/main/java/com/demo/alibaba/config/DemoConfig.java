package com.demo.alibaba.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: Demo
 * @Description: demo
 * @Author: lichangtong
 * @Date: 2020-09-04 17:36
 */

//@Component
//@Data
//@RefreshScope
public class DemoConfig {
    @Value(value = "${demo.namespace}")
    private String namespace;
}
