package com.demo.alibaba.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: PayRequest
 * @Description: 支付查询接口
 * @Author: lichangtong
 * @Date: 2020-10-14 16:00
 */
@Data
public class QueryPayRequest implements Serializable {

	@NotNull(message = "收款渠道编码不能为空")
	private String channelCode;
	@NotNull(message = "交易号不能为空")
	private String tradeNo;

}
