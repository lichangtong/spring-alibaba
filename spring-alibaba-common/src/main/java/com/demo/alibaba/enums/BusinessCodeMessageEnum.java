package com.demo.alibaba.enums;

import lombok.Getter;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: SystemCodeMessageEnum
 * @Description: 业务级枚举信息
 * @Author: lichangtong
 * @Date: 2020-11-05 11:18
 */

public enum BusinessCodeMessageEnum implements ICodeMessage {
    /**
     * 业务级错误
     */
    SUCCESS(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMessage()),
    USER_NAME_ERROR(400001, "用户名错误"),
    USER_PWD_ERROR(400002, "密码错误"),
    USER_EMAIL_ERROR(400003, "邮箱错误"),
    USER_DISABLE_ERROR(400004, "用户已被禁用"),
    /**
     * 权限控制
     */
    USER_NOT_AUTH_ERROR(600001, "用户未授权");

    @Getter
    private Integer code;
    @Getter
    private String message;

    BusinessCodeMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
