package com.alpha.classpie.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Component
@Data
@Validated
public abstract class RegisterDto<T>  {
    protected T captcha;
    @NotBlank
    protected String virtualId;
    protected String registerUsername;
}
