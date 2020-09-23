package com.demo.alibaba.service;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author lichangtong
 * @version 1.0
 * @date 2020/9/23 10:14
 * @description 用户支付接口
 */

public interface IUserPayService extends InitializingBean {
    /**
     * @return
     * @throws
     * @author lichangtong
     * @version 1.0
     * @date 2020/9/23 10:16
     * @description 统一下单接口
     */

    void unifiedOrder();

    /**
     * @return
     * @throws
     * @author lichangtong
     * @version 1.0
     * @date 2020/9/23 10:16
     * @description 统一接口查询订单状态
     */

    void queryOrderStatus();

    /**
     * @return
     * @throws
     * @author lichangtong
     * @version 1.0
     * @date 2020/9/23 10:17
     * @description 获取可以用的支付方式
     */

    void queryPayType();
}
