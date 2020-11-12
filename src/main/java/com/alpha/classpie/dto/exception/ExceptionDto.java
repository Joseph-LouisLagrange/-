package com.alpha.classpie.dto.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/10/15
 */

@Data
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties({"cause","stackTrace","localizedMessage","message","suppressed"})
public class ExceptionDto extends RuntimeException {
    Integer code;
    String name;
    String reason;
    String howDeal;

    public ExceptionDto(){}

    public ExceptionDto(Integer code, String name, String reason, String howDeal) {
        this(name,reason,howDeal);
        this.code = code;

    }

    public ExceptionDto(String name, String reason, String howDeal) {
        super(name,new Throwable(reason));
        this.name = name;
        this.reason = reason;
        this.howDeal = howDeal;
    }


    public ExceptionDto(Exception e) {
        this(e.getMessage(),e.getCause(),true,true);
    }

    public ExceptionDto(String message, Throwable cause) {
        this(message,cause,true,true);
    }

    public ExceptionDto(Throwable cause) {
        this(null,cause);
    }

    public ExceptionDto(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        if(cause!=null)
            this.setName(cause.getClass().getSimpleName());
        this.setReason(this.getMessage());
    }
}
