package com.demo.alibaba.service.impl;

import com.demo.alibaba.enums.UserPayTypeEnum;
import com.demo.alibaba.service.IUserPayService;
import com.demo.alibaba.service.UserPayServiceStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserPayOnlineServiceImpl
 * @Description: 用户线下支付
 * @Author: lichangtong
 * @Date: 2020-09-23 10:24
 */
@Service
@Slf4j
public class UserPayOfflineServiceImpl implements IUserPayService {
    @Override
    public void unifiedOrder() {
        log.info("UserPayOfflineServiceImpl 订单创建");
    }

    @Override
    public void queryOrderStatus() {
        log.info("UserPayOfflineServiceImpl 订单查询状态");
    }

    @Override
    public void queryPayType() {
        log.info("UserPayOfflineServiceImpl 订单查询支付方式");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory.register(UserPayTypeEnum.USER_OFFLINE_PAY.getUserPayTypeame(), this);
    }
}
