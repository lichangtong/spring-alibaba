package com.demo.alibaba.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: ApiResult
 * @Description: 统一返回结果类
 * @Author: lichangtong
 * @Date: 2020-09-03 18:16
 */
@Builder(toBuilder = true)
@Data
public class ApiResult<T> implements Serializable {
	private String message;
	private Integer code;
	private T data;
}
