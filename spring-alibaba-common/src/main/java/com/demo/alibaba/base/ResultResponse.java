package com.demo.alibaba.base;

import com.demo.alibaba.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Program: IntelliJ IDEA 构建者模式，处理统一结果
 * @ClassName: ResultResponse
 * @Description: 统一结果返回类
 * @Author: lichangtong
 * @Date: 2020-11-05 14:17
 */
@Data
public class ResultResponse<T> implements Serializable {
    /**
     * 请求状态码  参考 @StatusEnum
     */
    private Integer status;
    /**
     * 业务处理状态码  ICodeMessage 子类
     */
    private Integer code;
    /**
     * 业务处理结果
     */
    private String message;
    /**
     * 业务处理数据
     */
    private T data;


    private ResultResponse(Builder<T> builder) {
        this.status = builder.status;
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static class Builder<T> {
        private Integer status;
        private Integer code;
        private String message;
        private T data;

        public Builder success() {
            this.status = StatusEnum.SUCCESS.getCode();
            this.code = StatusEnum.SUCCESS.getCode();
            this.message = StatusEnum.SUCCESS.getMessage();
            return this;
        }

        public Builder success(T data) {
            this.status = StatusEnum.SUCCESS.getCode();
            this.code = StatusEnum.SUCCESS.getCode();
            this.message = StatusEnum.SUCCESS.getMessage();
            this.data = data;
            return this;
        }

        public Builder fail() {
            this.status = StatusEnum.PARAM_ERROR.getCode();
            this.code = StatusEnum.PARAM_ERROR.getCode();
            this.message = StatusEnum.PARAM_ERROR.getMessage();
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public ResultResponse<T> build() {
            return new ResultResponse<T>(this);
        }
    }
}
