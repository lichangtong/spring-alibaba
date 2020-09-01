package com.alibaba.demo.feign;

import com.alibaba.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@FeignClient(value = "consumer-server", fallback = ConsumerServiceImpl.class)
public interface ConsumerService {

    @PostMapping(value = "/consumer/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    String getUser(@RequestBody @Valid User user);

}
