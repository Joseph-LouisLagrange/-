package com.alpha.classpie.aop;


import com.alpha.classpie.annotation.NoDto;
import com.alpha.classpie.dto.exception.ResultDto;
import com.alpha.classpie.dto.ResultDtoAdapter;

import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author 杨能
 * @create 2020/10/15
 */
@ControllerAdvice
public class DefaultControllerAdvise implements ResponseBodyAdvice<Object> , RequestBodyAdvice {

    @Resource(name = "defaultResultDtoAdapter")
    ResultDtoAdapter<Object> resultDtoAdapter;



    static final String controllerPath="com.alpha.classpie.controller";

    /**
     * 对全局异常的处理
     * @param e 异常对象
     * @return 数据传输的结果对象
     */
    @ResponseBody
    @ExceptionHandler
    public ResultDto exceptionHandler(Exception e) throws Exception {
        return resultDtoAdapter.toResultDto(e);
    }


    /**
     * 设定完全支持
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 把数据转化为传输对象
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //变为dto
        Package p=methodParameter.getDeclaringClass().getPackage();
        if(!methodParameter.hasMethodAnnotation(NoDto.class)
                && p.getName().startsWith(controllerPath)
                && !(o instanceof ResultDto)){
            ResultDto resultDto = resultDtoAdapter.toResultDto(o);
            if(o instanceof String){
                return (new Gson()).toJson(resultDto);
            }
            return resultDto;
        }
        return o;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
