package com.alpha.classpie.controller;

import com.alpha.classpie.annotation.NoDto;
import com.alpha.classpie.config.sms.SmsCodeAuthenticationToken;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.dto.safeimpl.DataWrapper;

import com.alpha.classpie.dto.safeimpl.UserSafeWrapper;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.impl.SimpleUserService;
import com.alpha.classpie.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author 杨能
 * @create 2020/11/4
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public static User getUser(){
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Resource(name = "defaultUserDetails")
    UserDetailsService userService;

    @Resource(name = "defaultUserService")
    SimpleUserService simpleUserService;

    public static final String USERID ="USERID";

    @Autowired
    DataWrapper dataWrapper;


    @RequestMapping("/login")
    @PreAuthorize("permitAll()")
    public void login(){
        throw new ExceptionDto("访问异常","权限不足或者登陆状态异常","退出当前账户并重新登陆");
    }

    @RequestMapping("/loginFail")
    public String loginFail(){
        return "失败";
    }

    /**
     * 这里统一颁发token
     * @param response
     * @return
     */
    @RequestMapping("/loginSuccess")
    public String loginSuccess(HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=null;
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            user = (User) authentication.getPrincipal();
        }else if(authentication instanceof SmsCodeAuthenticationToken){
            //短信登陆
             user= (User) authentication.getDetails();
        }else {
            throw new ExceptionDto("认证异常","未知的认证令牌","重新登陆");
        }
        String token = JwtTokenUtil.createToken(user.getUsername(),user.getId(),user.getAuthorities());

        // 在请求头里返回创建成功的token
        // 设置请求头为带有"Bearer "前缀的token字符串
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        return "成功";
    }


    @NoDto
    @PreAuthorize("isAnonymous()")
    @RequestMapping("/usernamePasswordLoginProcess")
    public boolean loginProcess(@RequestParam("username")String username,@RequestParam("password")String password){
        UserDetails userDetails = userService.loadUserByUsername(username);
        //SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities()));
        return true;
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/getMe")
    public UserSafeWrapper getMe(){
        User user=getUser();
        System.out.println(user);
        return dataWrapper.doUserSafeWrap(user);
    }
}
