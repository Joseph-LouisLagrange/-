package com.alpha.classpie.dto.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/10/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FailResultDto extends ResultDto {
    private ExceptionDto exception;

    public FailResultDto(ExceptionDto exceptionDto){
        this.setException(exceptionDto);
    }
}
