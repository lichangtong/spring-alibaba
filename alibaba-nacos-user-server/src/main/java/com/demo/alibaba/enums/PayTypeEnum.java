package com.demo.alibaba.enums;

/**
 * @author lichangtong
 * @version 1.0
 * @date 2020/10/12 10:53
 * @description 支付渠道信息
 */

public enum PayTypeEnum {
    CBC_POS(4, 2, "建行POST机"),
    MEITUAN_POS(5, 2, "建行POST机"),
    ICBC_TRANSFER(6, 2, "建行POST机"),
    UNION_PAY(7, 2, "建行POST机");
    private Integer payType;
    private Integer payWay;
    private String payTypeName;

    PayTypeEnum(Integer payType, Integer payWay, String payTypeName) {
        this.payType = payType;
        this.payWay = payWay;
        this.payTypeName = payTypeName;
    }
}
