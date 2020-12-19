package com.alpha.classpie.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.Policy;
import java.util.*;


@Aspect
@Component
public class WebLogAdvise {
    @Resource(name = "webLogger")
    Logger logger;

    @Pointcut("execution( * com.alpha.classpie.controller..*(..))")
    public void WebLogAspect(){}

    @Before("WebLogAspect()")
    public void doBefore(JoinPoint joinPoint){
        //启用数据安全的入站策略
    }

    @After("WebLogAspect()")
    public void doAfter(JoinPoint joinPoint){

    }

    @AfterReturning("WebLogAspect()")
    public void doAfterReturning(JoinPoint joinPoint){

    }

    @AfterThrowing("WebLogAspect()")
    public void deAfterThrowing(JoinPoint joinPoint){

    }

    @Around("WebLogAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        //记录web访问
        webLog(joinPoint);
        return joinPoint.proceed();
    }

    public String aggregate(Method method,Object...args){
        StringBuilder stringBuffer=new StringBuilder();
        Parameter[] parameters=method.getParameters();
        stringBuffer.append(method.getName()).append('(');
        List<String> argAggregations=new ArrayList<>();
        for(int i=0;i<parameters.length&&i<args.length;i++){
            argAggregations.add(args[i]+"->"+parameters[i].getName());
        }
        stringBuffer.append(String.join(" , ",argAggregations));
        stringBuffer.append(')');
        return stringBuffer.toString();
    }



    public void webLog(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getRemoteAddr();
        logger.debug("-----------------------------start----------------------------------");
        logger.debug("ip=======>{}",ip);
        String path=request.getServletPath();
        logger.debug("path=======>{}",path);
        String requestMethod = request.getMethod();
        logger.debug("method=======>{}",requestMethod);
        String characterEncoding = request.getCharacterEncoding();
        logger.debug("characterEncoding=======>{}",characterEncoding);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName=headerNames.nextElement();
            String headerValue=request.getHeader(headerName);
            logger.debug("header=======>name:{},value:{}",headerName,headerValue);
        }
        String queryString = request.getQueryString();
        logger.debug("queryString=======>{}",queryString);

        String contentType = request.getContentType();
        if(contentType!=null) {
            if (contentType.contains("multipart")) {
                try {
                    Collection<Part> parts = request.getParts();
                    parts.forEach(part -> logger.debug("part=======>name:{},size:{},contentType:{}"
                            , part.getName(), part.getSize(), part.getContentType()));
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else if (contentType.contains("form-urlencoded")) {
                //使用key/value
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String parameterName = parameterNames.nextElement();
                    String[] parameterValues = request.getParameterValues(parameterName);
                    logger.debug("请求键值=======>key:{},value:{}", parameterName, Arrays.toString(parameterValues));
                }
            }
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        logger.debug("访问的Controller方法=======>{}",method.getName());
        if(method.getParameterCount()>0){
            //打印参数注入情况
            Object[] args = joinPoint.getArgs();
            if(args!=null)
                logger.debug(aggregate(method,args));
        }
        logger.debug("-----------------------------end----------------------------------");
    }
}
