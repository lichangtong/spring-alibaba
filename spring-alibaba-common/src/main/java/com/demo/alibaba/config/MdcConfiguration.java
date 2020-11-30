package com.demo.alibaba.config;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: MdcConfiguration
 * @Description: MdcConfiguration
 * @Author: lichangtong
 * @Date: 2020-11-28 11:15
 */

////@Configuration
//public class MdcConfiguration {
//	@Bean
//	@ConditionalOnMissingClass(value = {"com.hymm.filter.MdcFilter"})
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		//注入过滤器
//		registration.setFilter(new MdcFilter());
//		//拦截规则
//		registration.addUrlPatterns("/*");
//		//过滤器名称
//		registration.setName("mdcFilter");
//		//过滤器顺序
//		registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
//		return registration;
//	}
//}
