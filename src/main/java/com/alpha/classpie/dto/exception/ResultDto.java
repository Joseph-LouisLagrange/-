package com.alpha.classpie.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ResultDto {
    protected boolean isSuccess ;
}
