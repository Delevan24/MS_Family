package com.eternity.microservice.demo1.annotation;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopConfiguration {
	
	@Around("@annotation(DoSomeThing)")
	public void doBefore(ProceedingJoinPoint pjp) throws Throwable {
		Signature signature = pjp.getSignature();//此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
		MethodSignature methodSignature = (MethodSignature) signature;//获取参数名
		DoSomeThing ypjglog = methodSignature.getMethod().getAnnotation(DoSomeThing.class);
		System.out.println(ypjglog.describe());
		try {
			 pjp.proceed(); //直接pjp.proceed()，没有return ，前台页面获取不到ajax数据
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
