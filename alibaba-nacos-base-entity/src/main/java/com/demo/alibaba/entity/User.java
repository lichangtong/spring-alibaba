package com.demo.alibaba.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: User
 * @Description: 用户信息实体
 * @Author: lichangtong
 * @Date: 2020-09-03 10:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String mobile;
    private String address;
}
