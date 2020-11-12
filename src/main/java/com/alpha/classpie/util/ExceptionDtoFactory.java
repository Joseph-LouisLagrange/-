package com.alpha.classpie.util;



import com.alpha.classpie.dto.exception.ExceptionDto;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author 杨能
 * @create 2020/10/15
 */
public class ExceptionDtoFactory {
    public ExceptionDto unknown;
    //几乎全是在读
    public Map<Integer,ExceptionDto> exceptionDtoMap=new Hashtable<>();

    {
        exceptionDtoMap.put(1,new ExceptionDto(1,"验证码不存在","过期或者未发送验证码","重新发送验证码到手机"));
        exceptionDtoMap.put(2,new ExceptionDto(2,"验证码不存在","过期或者未发送验证码","重新发送验证码到手机"));
    }

    public ExceptionDtoFactory(){
        unknown = new ExceptionDto(0,"未知异常","尝试获取未被注册的异常对象","服务端请检查是否正确获取异常对象");
    }

    public ExceptionDto getExceptionDtoByCode(Integer code){
        ExceptionDto exceptionDto=exceptionDtoMap.get(code);
        if(exceptionDto==null){
            exceptionDto=unknown;

            //弹出栈
            //unknown.getStackTrace();
            //exceptionDto=unknown;
        }
        return exceptionDto;
    }

    public void put(Integer code , ExceptionDto exceptionDto){
        this.exceptionDtoMap.put(code, exceptionDto);
    }
}
