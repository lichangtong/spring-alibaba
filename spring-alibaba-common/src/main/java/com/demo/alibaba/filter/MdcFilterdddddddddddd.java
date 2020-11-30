//package com.demo.alibaba.filter;
//
//import org.slf4j.MDC;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.UUID;
//
//public class MdcFilter implements Filter {
//
//	public static final String TRACE_ID = "traceId";
//	public static final String MESSAGE = "message";
//	public static final String PROJECT = "project";
//	@Value("${spring.application.name:Not-configured}")
//	private String application;
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("=================");
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		String uuid = UUID.randomUUID().toString().replace("-", "");
//
//		MDC.put(TRACE_ID, uuid);
//		MDC.put(MESSAGE, "request" + request.getRequestURI());
//		MDC.put(PROJECT, application);
//		try {
//			filterChain.doFilter(servletRequest, servletResponse);
//		} finally {
//			//保证一次请求一个唯一标识
//			MDC.remove(TRACE_ID);
//			MDC.remove(MESSAGE);
//			MDC.remove(PROJECT);
//		}
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//}
