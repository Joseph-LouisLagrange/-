package com.alpha.classpie.config.security;

import com.alpha.classpie.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }



    /**
     * 验证操作 接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 从输入流中获取到登录的信息
        // 创建一个token并调用authenticationManager.authenticate() 让Spring security进行验证
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getParameter("username"),request.getParameter("password")));
    }

    /**
     * 验证【失败】调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
