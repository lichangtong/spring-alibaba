package com.demo.alibaba.filter;

import com.hymm.filter.MdcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: TestMdcConfiguration
 * @Description: TestMdcConfiguration
 * @Author: lichangtong
 * @Date: 2020-11-28 16:57
 */
@Configuration
public class TestMdcConfiguration {
	@Bean
	public FilterRegistrationBean mdcFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		//注入过滤器
		registration.setFilter(new MdcFilter("--------"));
		//拦截规则
		registration.addUrlPatterns("/*");
		//过滤器名称
		registration.setName("mdcFilter");
		//过滤器顺序
		registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
		return registration;
	}
}
