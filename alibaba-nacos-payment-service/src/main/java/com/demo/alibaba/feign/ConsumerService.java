package com.demo.alibaba.feign;

import com.demo.alibaba.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@FeignClient(value = "consumer-server",path = "consumer-server/consumer",fallback = ConsumerServiceImpl.class)
public interface ConsumerService {

    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    String queryUser(@Valid @RequestBody User user);

}
