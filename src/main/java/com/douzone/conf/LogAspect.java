package com.douzone.conf;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(public * com.douzone..*.*(..))")
	public void publicTarget() { }
	
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("[method]: " + joinPoint.getSignature().toLongString());
		Arrays.asList(joinPoint.getArgs()).forEach(arg -> logger.debug("[Arg]: " + arg));
		long start = System.nanoTime();
		Object result = joinPoint.proceed();
		logger.debug("[time]: " + joinPoint.getSignature().toLongString() + " - " + (System.nanoTime() - start));
		logger.debug("[return] " + result);
		return result;
	}
}