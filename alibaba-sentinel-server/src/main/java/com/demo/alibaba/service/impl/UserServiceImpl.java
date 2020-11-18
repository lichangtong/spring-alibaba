package com.demo.alibaba.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.service.IUserService;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserServiceImpl
 * @Description: UserServiceImpl
 * @Author: lichangtong
 * @Date: 2020-11-14 14:22
 */

public class UserServiceImpl implements IUserService {
	@Override
	public @NonNull List getList(Integer userId) {
		JSON.parseObject("", String.class);
		Objects.nonNull(userId);
		return null;
	}
}
