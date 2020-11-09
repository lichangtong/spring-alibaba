package com.demo.alibaba.pay;

import com.demo.alibaba.request.CallBackPayRequest;
import com.demo.alibaba.request.PayRequest;
import com.demo.alibaba.request.QueryPayRequest;
import com.demo.alibaba.result.ApiResult;

/**
 * 支付处理结果接口
 */
public interface IPayService {
	/**
	 * 各个渠道支付处理方法
	 *
	 * @param payRequest
	 * @return
	 */
	ApiResult toPay(PayRequest payRequest);

	/**
	 * 各个渠道处理支付结果请求
	 *
	 * @param request
	 * @return
	 */
	ApiResult queryPay(QueryPayRequest request);

	/**
	 * 处理支付回调结果
	 *
	 * @param payRequest
	 * @return
	 */
	ApiResult callBackPay(CallBackPayRequest payRequest);

	/**
	 * 检查是否有配置该支付渠道
	 *
	 * @param channelCode
	 * @return
	 */
	boolean checkChannelCode(String channelCode);

}
