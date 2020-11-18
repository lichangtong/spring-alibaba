package com.demo.alibaba.factory;

import com.demo.alibaba.enums.ChannelCodeEnum;
import com.demo.alibaba.pay.IPayService;
import com.demo.alibaba.request.CallBackPayRequest;
import com.demo.alibaba.request.PayRequest;
import com.demo.alibaba.request.QueryPayRequest;
import com.demo.alibaba.result.ApiResult;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: PaymentFactory
 * @Description: 支付工厂
 * @Author: lichangtong
 * @Date: 2020-11-09 15:35
 */

public class PaymentFactory {
	private static Map<String, IPayService> payServices = new ConcurrentHashMap<String, IPayService>();

	public static ApiResult toPay(PayRequest request) {

		if (checkChannelCode(request.getChannelCode())) {
			return payServices.get(request.getChannelCode()).toPay(request);
		}
		return returnApiResult(false);
	}

	public static ApiResult queryPay(QueryPayRequest request) {
		if (checkChannelCode(request.getChannelCode())) {
			return payServices.get(request.getChannelCode()).queryPay(request);
		}
		return returnApiResult(false);
	}

	public static ApiResult callBackPay(CallBackPayRequest request) {
		if (checkChannelCode(request.getChannelCode())) {
			return payServices.get(request.getChannelCode()).callBackPay(request);
		}
		return returnApiResult(false);
	}

	public static void register(String payTypeCode, IPayService payService) {
		Assert.notNull(payService, "IPayService 支付业务处理类");
		payServices.put(payTypeCode, payService);
	}

	/**
	 * 各个渠道检查
	 *
	 * @param channelCode
	 * @return
	 */
	private static boolean checkChannelCode(String channelCode) {
		return Optional.ofNullable(ChannelCodeEnum.getChanneByCode(channelCode)).isPresent();
	}

	private static ApiResult returnApiResult(boolean bo) {
		return ApiResult.builder().build();
	}
}
