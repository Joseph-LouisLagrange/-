package com.alpha.classpie.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Component
@Data
public abstract class RegisterDto<T>  {
    protected T captcha;
    protected String registerUsername;
}
