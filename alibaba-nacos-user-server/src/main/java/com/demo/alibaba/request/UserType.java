package com.demo.alibaba.request;

import com.demo.alibaba.interfaces.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserType
 * @Description: 用户类型
 * @Author: lichangtong
 * @Date: 2020-09-23 10:53
 */
@Data
public class UserType implements Serializable {
    @NotNull(groups = ValidGroup.class, message = "userType 不能为空")
    private Integer userType;
}
