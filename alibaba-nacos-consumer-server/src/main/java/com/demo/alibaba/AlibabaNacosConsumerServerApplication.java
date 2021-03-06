package com.demo.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author admin
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AlibabaNacosConsumerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosConsumerServerApplication.class, args);
    }

}
