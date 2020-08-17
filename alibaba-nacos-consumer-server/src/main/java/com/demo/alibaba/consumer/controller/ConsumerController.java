package com.demo.alibaba.consumer.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @NacosValue(value = "${demo.namespace}")
    private String namespace;
    @RequestMapping("/hello")
    public String helloConsumer() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider-service");
        RestTemplate restTemplate = new RestTemplate();
        String url = serviceInstance.getUri() + "/provider/hello";
        String result = restTemplate.getForObject(url, String.class);
        return "hello - consumer :result:"+result+" = = "+namespace;
    }
}
