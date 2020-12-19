package com.alpha.classpie.config.security;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint , Serializable {

    private static final long serialVersionUID = -8970718410437077606L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        Gson gson=new Gson();
        throw new ExceptionDto(authException);
        //response.getWriter().print(gson.toJson(authException));
    }
}
