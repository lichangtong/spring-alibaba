package com.demo.alibaba.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lichangtong
 * @version 1.0
 * @date 2020/10/12 10:53
 * @description 支付渠道信息
 */

public enum ChannelCodeEnum {
	WX_PAY("100100", "微信"),
	ALI_PAY("100200", "支付宝"),
	POS_PAY("100300", "POS机"),
	TRANSFER_PAY("100400", "转账"),
	JD_PAY("100500", "京东");
	private String channelCode;
	private String channelName;

	ChannelCodeEnum(String channelCode, String channelName) {
		this.channelCode = channelCode;
		this.channelName = channelName;
	}

	public static ChannelCodeEnum getChanneByCode(String channelCode) {

		if (StringUtils.isEmpty(channelCode)) {
			return null;
		}
		for (ChannelCodeEnum value : values()) {
			if (value.getChannelCode().equalsIgnoreCase(channelCode)) {
				return value;
			}
		}
		return null;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public String getChannelName() {
		return channelName;
	}
}
