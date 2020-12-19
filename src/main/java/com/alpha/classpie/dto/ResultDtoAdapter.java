package com.alpha.classpie.dto;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.dto.exception.ResultDto;
import org.springframework.stereotype.Component;

@Component
public interface ResultDtoAdapter<T> {
    public T toData(ResultDto resultDto);

    public ExceptionDto toExceptionDto(ResultDto resultDto);

    public ResultDto toResultDto(T data);

    public ResultDto toResultDto(ExceptionDto exceptionDto);

    public ResultDto toResultDto(Exception e);
}
