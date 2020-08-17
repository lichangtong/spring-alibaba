package com.demo.alibaba.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacosProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosProviderServerApplication.class, args);
    }

}
