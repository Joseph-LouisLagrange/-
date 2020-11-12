package com.alpha.classpie.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ResultDto {
    protected boolean isSuccess ;
}
