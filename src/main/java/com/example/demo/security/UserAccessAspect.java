package com.example.demo.security;

import com.example.demo.utility.AuthorizationCheck;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class UserAccessAspect {

	@Autowired
	private AuthorizationCheck authorizationCheck;


	@Around("@annotation(com.example.demo.security.CustomAnnotation)")
	public Object trackTime(ProceedingJoinPoint jointPoint) throws Throwable {
		boolean debug=false;
		long stratTime=System.currentTimeMillis();
		Method m = MethodSignature.class.cast(jointPoint.getSignature()).getMethod();
		for (Annotation a : m.getDeclaredAnnotations()) {
			if (a instanceof CustomAnnotation) {
				CustomAnnotation annot = (CustomAnnotation) a;
				debug=annot.debug();
			}
		}

		long endTime=System.currentTimeMillis();
		if(debug) {
			System.out.println("Debug is true");
			//authorizationCheck.verifyRole("bankRequest","update");
			throw new RuntimeException("Not Authorised");
		} else {
			System.out.println("Debug is False");
		}
		Object obj=jointPoint.proceed();
		System.out.println("Method is Not invoked");
		return obj;
	}

}
