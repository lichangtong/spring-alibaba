package com.demo.alibaba.controller;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.factory.PaymentFactory;
import com.demo.alibaba.request.PayRequest;
import com.demo.alibaba.request.QueryPayRequest;
import com.demo.alibaba.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: PayController
 * @Description: 支付处理类
 * @Author: lichangtong
 * @Date: 2020-11-09 15:59
 */

@RequestMapping(value = "/pay")
@RestController
@Slf4j
public class PayController {

	@RequestMapping(path = "/v1/toPay", method = RequestMethod.POST)
	public ApiResult toPay(@Validated @RequestBody PayRequest payRequest) {
		System.out.println("payRequest --> {}" + JSON.toJSONString(payRequest));
		log.info("payRequest --> {}", JSON.toJSONString(payRequest));
		return PaymentFactory.toPay(payRequest);
	}

	@RequestMapping("/v1/queryPay")
	public ApiResult queryPay(@Validated @RequestBody QueryPayRequest request) {
		return PaymentFactory.queryPay(request);
	}

}
