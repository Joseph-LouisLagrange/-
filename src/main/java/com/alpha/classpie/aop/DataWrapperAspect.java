package com.alpha.classpie.aop;

import com.alpha.classpie.dto.DataWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/11/23
 */
@Aspect
@Component
public class DataWrapperAspect {

    @Pointcut("execution( * com.alpha.classpie.dto.DataWrapper.*(..))")
    public void getDataWrapperAspect(){};
    @Around("getDataWrapperAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        return joinPoint.proceed();
    }
}
