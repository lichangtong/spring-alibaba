package com.demo.alibaba.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacosConsumerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosConsumerServerApplication.class, args);
    }

}
