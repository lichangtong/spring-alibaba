package com.demo.alibaba.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: ApiResult
 * @Description: 统一返回结果类
 * @Author: lichangtong
 * @Date: 2020-09-03 18:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {
    private String message;
    private Integer code;
    private T data;
}
