package com.demo.alibaba.controller;

import com.demo.alibaba.factory.PaymentFactory;
import com.demo.alibaba.request.PayRequest;
import com.demo.alibaba.request.QueryPayRequest;
import com.demo.alibaba.result.ApiResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class PayController {

	@RequestMapping("/v1/topay")
	public ApiResult toPay(@Validated @RequestBody PayRequest payRequest) {
		return PaymentFactory.toPay(payRequest);
	}

	@RequestMapping("/v1/queryPay")
	public ApiResult queryPay(QueryPayRequest request) {
		return PaymentFactory.queryPay(request);
	}

}
