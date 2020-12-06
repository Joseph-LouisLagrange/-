package com.alpha.classpie.service.impl.safe;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.rdao.VirtualSession;
import com.alpha.classpie.service.inf.safe.UsernameSafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author 杨能
 * @create 2020/11/13
 */
@Service("defaultUsernameSafeService")
public class UsernameSafeServiceImpl implements UsernameSafeService {
    @Autowired
    VirtualSession virtualSession;

    private static final String REGISTER_USERNAME ="registerUsername";

    private static final String LOGIN_USERNAME="loginUsername";
    @Override
    public void doRegisterUsernameSafe(String virtualId, String username) {
        virtualSession.setAttributeForever(virtualId, REGISTER_USERNAME,username);
    }

    @Override
    public void checkRegisterUsernameSafe(String virtualId, String username) {
        String attribute = virtualSession.getAttribute(virtualId, REGISTER_USERNAME, String.class);
        if (attribute==null){
            throw new ExceptionDto("非法验证","当前的账号并未被发送验证码","重新进行注册");
        }
        if(!attribute.equals(username)){
            throw new ExceptionDto("验证失败","当前的账号与被发送验证码的账号不符","重新进行注册");
        }
    }

    @Override
    public void doLoginUsernameSafe(String virtualId, String username) {
        virtualSession.setAttributeForever(virtualId, LOGIN_USERNAME,username);
    }

    @Override
    public void checkLoginUsernameSafe(String virtualId, String username) {
        String attribute = virtualSession.getAttribute(virtualId, LOGIN_USERNAME, String.class);
        Assert.notNull(attribute,"登陆的username:["+username+"]不存在");
        if(!attribute.equals(username)){
            throw new ExceptionDto("注册失败","当前的注册账号与被发送验证码的账号不符","重新进行注册");
        }
    }
}
