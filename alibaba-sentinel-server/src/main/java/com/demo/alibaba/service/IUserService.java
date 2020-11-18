package com.demo.alibaba.service;

import lombok.NonNull;

import java.util.List;

public interface IUserService {
	@NonNull List getList(Integer userId);
}
