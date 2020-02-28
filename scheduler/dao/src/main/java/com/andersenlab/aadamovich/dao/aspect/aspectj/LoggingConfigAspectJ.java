package com.andersenlab.aadamovich.dao.aspect.aspectj;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalTime;

@Aspect
public class LoggingConfigAspectJ {

    private final Logger logger = LogManager.getLogger(LoggingConfigAspectJ.class);

    @Pointcut("execution(* com.andersenlab.aadamovich.dao.user.DefaultUserDaoImpl.* (..))")
    public void userDaoMethods() {
    }

    @Before("userDaoMethods()")
    public void logMethodCall (JoinPoint joinPoint) {
        final Signature signature = joinPoint.getSignature();
        final String className = signature.getDeclaringType().getSimpleName();
        final String methodName = signature.getName();
        final LocalTime currentTime = LocalTime.now();
        logger.info("CLASS: " + className + " --> METHOD: " +methodName + " --> TIME: " + currentTime);
    }
}
