package com.demo.alibaba.enums;

import lombok.Getter;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: StatusEnum
 * @Description: 请求状态枚举值
 * @Author: lichangtong
 * @Date: 2020-11-05 11:18
 */

public enum StatusEnum implements ICodeMessage {

    SUCCESS(200, "成功"),
    PARAM_ERROR(400, "参数错误"),
    SYSTEM_ERROR(500, "系统级错误"),
    NOT_AUTH_ERROR(600, "无权限");

    @Getter
    private Integer code;
    @Getter
    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
