package com.demo.alibaba.enums;

import lombok.Getter;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: SystemCodeMessageEnum
 * @Description: 系统级枚举信息
 * @Author: lichangtong
 * @Date: 2020-11-05 11:18
 */

public enum SystemCodeMessageEnum implements ICodeMessage {

    SUCCESS(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMessage()),
    SYSTEM_ERROR(500001, "服务器异常"),
    SYSTEM_DB_ERROR(500002, "数据获取失败");

    @Getter
    private Integer code;
    @Getter
    private String message;

    SystemCodeMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
