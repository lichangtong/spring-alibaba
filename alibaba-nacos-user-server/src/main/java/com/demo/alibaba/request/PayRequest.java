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

	@NotNull(message = "收款发起人Id不能为空")
	private Long originatorId;

	@NotNull(message = "收款发起人名字不能为空")
	private String originator;

	@NotNull(message = "收款渠道编码不能为空")
	private String channelCode;
	private String channelName;

	@NotNull(message = "支付方式不能为空")
	private Integer payWay;

	@NotNull(message = "支付金额不能为空")
	@Min(value = 1L, message = "支付金额不能少于1分")
	private BigDecimal payAmount = BigDecimal.ZERO;

	@NotNull(message = "订单号不能为空")
	private String orderNo;

	@NotNull(message = "客户端交易号不能为空")
	private String clientTradeNo;

	private String tradeNo;

	/**
	 * 线上支付时是回调交易号；线下支付是销售手动输入值 交易号/三方订单号/付款人姓名
	 * 需要区分判空
	 */
	private String evidence;

	@NotNull(message = "商品类型不能为空")
	private Integer goodsClassifyId;

	@NotNull(message = "商品类型名称不能为空")
	private String goodsClassifyName;

	@NotNull(message = "回调地址不能为空")
	private String callBackUrl;


	@NotNull(message = "付款人姓名")
	private String payerName;

	@NotNull(message = "付款人手机号")
	private String payerMobile;
	/**
	 * 线下收款时凭条地址不能为空
	 */
	private String evidenceUrl;
	/**
	 * 线下收款时实际收款时间不能为空，线上银行交易时间
	 */
	private String actualCollectionTime;

	@NotNull(message = "发起调用的唯一标识不能为空")
	private String uniqueId;

	@NotNull(message = "终端IP不能为空")
	private String terminalIp;

	@NotNull(message = "调用支付用户端不能为空")
	private Integer clientType;

	private String transactionNo;

	private String accountNo;
}
