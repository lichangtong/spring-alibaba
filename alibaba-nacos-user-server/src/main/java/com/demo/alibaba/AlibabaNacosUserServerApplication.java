package com.demo.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.hymm.*", "com.demo.alibaba.*"})
public class AlibabaNacosUserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosUserServerApplication.class, args);
    }

}
