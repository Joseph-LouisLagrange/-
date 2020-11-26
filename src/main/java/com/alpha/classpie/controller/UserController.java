package com.alpha.classpie.controller;

import com.alpha.classpie.config.sms.SmsCodeAuthenticationToken;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.dto.DataWrapper;

import com.alpha.classpie.dto.warpper.UserSafeWrapper;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.rdao.VirtualSession;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 杨能
 * @create 2020/11/4
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public static final String USERID="userId";

    public static final String TOKEN_HEADER="token";

    @Resource(name = "defaultUserService")
    UserService userService;

    //获取当前的用户
    public static User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication
                .getDetails();
    }

    public static int getUserId(){
        return getUser().getId();
    }


    @Autowired
    VirtualSession virtualSession;   //未认证前的临时虚拟session

    @Autowired
    DataWrapper dataWrapper;   //数据传输的包装器


    @RequestMapping("/login")
    @PreAuthorize("permitAll()")
    public void login(){
        throw new ExceptionDto("访问异常","权限不足或者登陆状态异常","退出当前账户并重新登陆");
    }

    @RequestMapping("/loginFail")
    public boolean loginFail(){
        return false;
    }

    /**
     * 这里统一颁发token
     * @param response
     * @return
     */
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/loginSuccess")
    public boolean loginSuccess(HttpServletResponse response){
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
        String token = JwtTokenUtil.createToken(user.getUsername(),user.getId());
        // 在请求头里返回创建成功的token
        // 设置请求头为带有"Bearer "前缀的token字符串
        response.setHeader(TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);
        return true;
    }


    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/getMe")
    public UserSafeWrapper getMe(){
        User user=getUser();
        return dataWrapper.doUserSafeWrap(user);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/getVirtualId")
    public String getVirtualId(){
        return UUID.randomUUID().toString();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/hasUsername")
    public boolean hasTelephone(@RequestParam(name = "username") String username){
        return userService.hasUsername(username);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/simpleUpdateUser")
    public User simpleUpdateUser(@Validated @RequestBody User user){
        return userService.updateUser(user,UserController.getUserId());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/unbindTelephone")
    public boolean unbindTelephone(@RequestParam(name = "password")String password){
        return userService.unbindTelephone(UserController.getUserId(),password);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/unbindEmail")
    public boolean unbindEmail(@RequestParam(name = "password")String password){
        return userService.unbindEmail(UserController.getUserId(),password);
    }

    @RequestMapping("/bindEmail")
    public boolean bindEmail(@RequestParam("email")String email,@RequestParam("verificationCode")int verificationCode){
        return userService.bindEmail(UserController.getUserId(),email,verificationCode);
    }

    @RequestMapping("/bindTelephone")
    public boolean bindTelephone(@RequestParam("phone")String phone,@RequestParam("verificationCode")int verificationCode){
        return userService.bindTelephone(UserController.getUserId(),phone,verificationCode);
    }

    @RequestMapping("/getSafeUser")
    public User getSafeUser(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "userId",required = false)Integer userId){
        User user=null;
        if(userId!=null){
            user=userService.getUserById(userId);
        }
        if(StringUtils.hasText(username)){
            user=userService.getUserByUsername(username);
        }
        if(user!=null){
           return dataWrapper.doUserSafeWrap(user);
        }
        return null;
    }
}
