package com.demo.alibaba.pay.impl;

import com.demo.alibaba.enums.ChannelCodeEnum;
import com.demo.alibaba.factory.PaymentFactory;
import com.demo.alibaba.pay.IPayService;
import com.demo.alibaba.request.CallBackPayRequest;
import com.demo.alibaba.request.PayRequest;
import com.demo.alibaba.request.QueryPayRequest;
import com.demo.alibaba.result.ApiResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: WxPayServiceImpl
 * @Description: 微信支付处理类
 * @Author: lichangtong
 * @Date: 2020-11-09 15:44
 */

@Service
public class WxPayServiceImpl implements IPayService, InitializingBean {
	@Override
	public ApiResult toPay(PayRequest payRequest) {
		return null;
	}

	@Override
	public ApiResult queryPay(QueryPayRequest request) {
		return null;
	}

	@Override
	public ApiResult callBackPay(CallBackPayRequest payRequest) {
		return null;
	}

	@Override
	public boolean checkChannelCode(String channelCode) {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		PaymentFactory.register(ChannelCodeEnum.WX_PAY.getChannelCode(), this);
	}
}
