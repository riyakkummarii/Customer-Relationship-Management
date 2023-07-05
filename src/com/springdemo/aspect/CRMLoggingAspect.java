package com.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger=Logger.getLogger(getClass().getName());

	
	// setup pointcut declarations
	
	// Match on any class in package "com.springdemo.controller", any number of arguments and any method in class
	@Pointcut("execution(* com.springdemo.controller.*.*(..))")
	public void forControllerPackage() {}
	
	// do the same for sevice and dao	
	
	@Pointcut("execution(* com.springdemo.service.*.*(..))")
	public void forServicePackage() {}
	
	@Pointcut("execution(* com.springdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	public void forAppFlow() {}
	
	// add @Before advice
	
	    @Before("forAppFlow()")
		public void before(JoinPoint theJoinPoint)
		{
			// display method we are calling
			
	    	String theMethod = theJoinPoint.getSignature().toShortString();
	    	myLogger.info("\n\n=====>> in @Before: calling method : "+theMethod+"\n\n");
	    	
			// display the arguments to the method
			
	    	Object[] args=theJoinPoint.getArgs();
	    	
	    	// add @AfterReturning advice
			
	    	for (Object tempArg : args)
	    	{
	    		myLogger.info("\n\n=====>> argument : "+tempArg+"\n\n");
	    	}
		}
	    
	    // add @AfterReturning advice
	    @AfterReturning(
	    		pointcut="forAppFlow()",
	    		returning="theResult")
	    public void afterReturning(JoinPoint theJoinPoint, Object theResult)
	    {
            // display method we are calling
			
	    	String theMethod = theJoinPoint.getSignature().toShortString();
	    	myLogger.info("\n\n=====>> in @AfterReturning: from method : "+theMethod+"\n\n");
	    	
	    	
	    	// display the data returned
	    	
	    	myLogger.info("\n\n=====>> Result : "+theResult+"\n\n");
	    }
	    
	    
	}
	
	

