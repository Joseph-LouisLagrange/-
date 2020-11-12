package com.alpha.classpie.dto;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.dto.exception.ResultDto;
import org.springframework.stereotype.Component;

/**
 * 数据传输格式适配器
 * @author 杨能
 * @create 2020/10/15
 */
@Component
public interface ResultDtoAdapter<T> {
    public T toData(ResultDto resultDto);

    public ExceptionDto toExceptionDto(ResultDto resultDto);

    public ResultDto toResultDto(T data);

    public ResultDto toResultDto(ExceptionDto exceptionDto);

    public ResultDto toResultDto(Exception e);
}
