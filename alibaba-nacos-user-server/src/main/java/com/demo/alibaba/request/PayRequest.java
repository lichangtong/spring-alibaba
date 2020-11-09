package com.demo.alibaba.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: PayRequest
 * @Description: 支付统一入参请求
 * @Author: lichangtong
 * @Date: 2020-10-14 16:00
 */
@Data
public class PayRequest implements Serializable {

	@NotNull(message = "门店Id不能为空")
	private Long storeId;

	@NotNull(message = "收款渠道编码不能为空")
	private String channelCode;
	private String channelName;

	@NotNull(message = "支付金额不能为空")
	@Min(value = 1L, message = "支付金额不能少于1分")
	private BigDecimal payAmount = BigDecimal.ZERO;

	@NotNull(message = "订单号不能为空")
	private String orderNo;

	@NotNull(message = "客户端交易号不能为空")
	private String clientTradeNo;

	private String tradeNo;
}
