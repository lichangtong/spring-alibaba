package com.alibaba.demo.feign;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public String getUser(User user) {
        log.info("ConsumerService start 服务不可用了，在这里采用降级失败，记录请求信息");
        log.info("request:{}", JSON.toJSONString(user));
        log.info("ConsumerService end");
        User user1 = new User("默认", "18888888888", "北京凯旋城");
        return JSON.toJSONString(user1);
    }
}
