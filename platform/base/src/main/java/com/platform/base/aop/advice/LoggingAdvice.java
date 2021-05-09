package com.platform.base.aop.advice;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.base.service.BaseService;
import com.platform.base.util.ConfigurationUtil;

@Aspect
@Component
public class LoggingAdvice {

	private Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Autowired
	private BaseService baseService;

	/*@Around("tenantPointCut()")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		if (configUtil.isAopLogging()) {
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] args = joinPoint.getArgs();
			logger.debug("ClassName : " + className + " :: MethodName : " + methodName);
			logger.debug("Arguments : " + Arrays.deepToString(args));
			Object object = joinPoint.proceed();
			return object;
		}
		return joinPoint.proceed();
	}*/

	// custom packages to execute
	// @Pointcut(value="execution(*
	// com.platform.base.util.ConfigurationUtil.isProdMode())")
//	@Pointcut(value = "within(@org.springframework.stereotype.Component *)")
//	public void loggingPointCut() {
//
//	}

	// Executing randomly, need to check pointcut expression
//	@Pointcut(value="execution(* com.platform.tenant.api.TenantController.*(..))")
//	@Pointcut(value="execution(* com.platform.tenant.serviceimplementation.TenantServiceImpl.findByTenantId(..))")
//	public void loggingPointCut1() {
//
//	}

	@Pointcut(value = "within(com.platform.tenant..*)")
	public void tenantPointCut() {

	}

	@Pointcut(value = "within(com.platform.user..*)")
	public void userPointCut() {

	}

	
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Instant start = Instant.now();
		Object proceed = joinPoint.proceed();
		Instant end = Instant.now();
		logger.info(joinPoint.getSignature() + " executed in " + Duration.between(start, end).toMillis() + "ms");
		return proceed;
	}

	@Around("@annotation(LogExecutionMethod) || tenantPointCut() || userPointCut()")
	public Object logExecutionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		if (ConfigurationUtil.isAopLogging()) {
			long start = System.currentTimeMillis();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] args = joinPoint.getArgs();
			logger.debug(
					(baseService != null && baseService.getTenantInfo() != null ? baseService.getTenantInfo().toString()
							: "defaultConfig") + " ClassName : " + className + " :: MethodName : " + methodName
							+ " Arguments : " + Arrays.deepToString(args));
			Object object = joinPoint.proceed();
			if (object != null) {
				logger.debug((baseService != null && baseService.getTenantInfo() != null
						? baseService.getTenantInfo().toString()
						: "defaultConfig") + " ClassName : " + className + " :: MethodName : " + methodName
						+ " Result : " + object.toString());
			}
			long executionTime = System.currentTimeMillis() - start;
			logger.debug(joinPoint.getSignature() + " executed in " + executionTime + "ms");
			return object;
		}
		return joinPoint.proceed();
	}

	/*
	 * @Pointcut(value = "within(com.platform.base.util.* *)") public void
	 * exceptionPointCut() {
	 * 
	 * }
	 * 
	 * @AfterThrowing(pointcut = "exceptionPointCut()", throwing = "e") public void
	 * logAfterThrowing(JoinPoint jp, Throwable e) {
	 * logger.error(jp.getSignature().getName() + " throwed an Exception : " + e !=
	 * null ? e.getMessage() : null); }
	 */
}
