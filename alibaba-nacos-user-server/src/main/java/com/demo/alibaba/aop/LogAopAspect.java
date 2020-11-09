package com.demo.alibaba.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: LogAopAspect
 * @Description: LogAopAspect 日志切面
 * @Author: lichangtong
 * @Date: 2020-11-09 17:11
 */

@Component
@Aspect
@Slf4j
public class LogAopAspect {


	@Pointcut(value = "execution( * com.demo.alibaba.controller..*.*(..))")
	public void point() {

	}

	@Around(value = "point()")
	public void beforeAdvice(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			System.out.println("payRequest --> {}" + arg);
			log.info("payRequest --> {}", arg);
		}
		log.info(JSON.toJSONString(joinPoint.getArgs()));
		try {
			joinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
