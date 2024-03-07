package com.winter.app.transfer.card;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Card {
	
	@Around("execution(* com.winter.app.transfer.*.*(..))")
	public Object checkCard(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("before check card");
		log.info("======================{}=============",joinPoint.getArgs());
		
		Object obj = joinPoint.proceed();
		log.info("======================{}=============",obj);
		
		
		System.out.println("after check card");
		return obj;
	}
}
