package com.alpha.classpie.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dao.StudentMapper;
import com.alpha.classpie.dao.UserMapper;
import com.alpha.classpie.dao.UserRoleMapper;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.UserExample;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.CaptchaService;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.type.UserNameType;
import com.alpha.classpie.util.FormatRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 杨能
 * @create 2020/11/1
 */

@Transactional
@Service("defaultUserService")
public class SimpleUserService implements UserService<com.alpha.classpie.pojo.user.User> {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    CaptchaService captchaService;

    @Resource(name = "defaultPasswordEncoder")
    PasswordEncoder passwordEncoder;

    public User wrap(User user){
        user.setAccountNumber(RandomUtil.randomString(9));
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    @Override
    public User smsRegister(User user, int verificationCode)  {
        if(captchaService.checkRegisterSMSCaptcha(user.getTelephoneNumber(), verificationCode)){
            userMapper.insertSelective(wrap(user));
            captchaService.deleteRegisterSMSCaptcha(user.getTelephoneNumber());
        }
        return user;
    }

    @Override
    public User emailRegister(User user, int verificationCode) {
        if(captchaService.checkRegisterEmailCaptcha(user.getEmailNumber(),verificationCode)){
            userMapper.insertSelective(wrap(user));
            captchaService.deleteRegisterEmailCaptcha(user.getEmailNumber());
        }
        return user;
    }


    @Override
    public User loginByAccountNumber(String accountNumber, String password) {
        return login(UserNameType.ACCOUNT,accountNumber,password);
    }

    @Override
    public User loginByEmailNumber(String emailNumber, String password) {
        return login(UserNameType.EMAIL,emailNumber,password);
    }

    @Override
    public User loginByTelephoneNumber(String telephoneNumber, String password) {
        return login(UserNameType.PHONE,telephoneNumber,password);
    }

    @Override
    public User loginBySMSVerificationCode(String telephoneNumber, int verificationCode) throws ExceptionDto {
        if(captchaService.checkLoginSMSCaptcha(telephoneNumber, verificationCode)){
            //检查通过
            UserExample userExample = new UserExample();
            userExample.createCriteria().andTelephoneNumberEqualTo(telephoneNumber);
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()>0){
                captchaService.deleteLoginSMSCaptcha(telephoneNumber);
                return users.get(0);
            }
        }
        return null;
    }

    @Override
    public User login(UserNameType userNameType, String userName, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria().andPasswordEqualTo(passwordEncoder.encode(password));
        if(userNameType == UserNameType.ACCOUNT){
            criteria.andAccountNumberEqualTo(userName);
        }else if(userNameType == UserNameType.EMAIL){
            criteria.andEmailNumberEqualTo(userName);
        }else{
            criteria.andTelephoneNumberEqualTo(userName);
        }
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>1){
            return users.get(0);
        }
        return null;
    }

    @Override
    @Cacheable("user")
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getUserByUserName(String username){
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(userNameType == UserNameType.ACCOUNT){
            criteria.andAccountNumberEqualTo(username);
        }else if(userNameType == UserNameType.EMAIL){
            criteria.andEmailNumberEqualTo(username);
        }else{
            criteria.andTelephoneNumberEqualTo(username);
        }
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }
    @Override
    public Set<Role> getRolesByUserId(int userId){
        return new HashSet<>(userRoleMapper.getRolesByUserId(userId));
    }

    @Override
    public int getUserIdByUsername(String username) {
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(userNameType == UserNameType.ACCOUNT){
            criteria.andAccountNumberEqualTo(username);
        }else if(userNameType == UserNameType.EMAIL){
            criteria.andEmailNumberEqualTo(username);
        }else{
            criteria.andTelephoneNumberEqualTo(username);
        }
        return userMapper.getUserId(userExample);
    }

    @Override
    public boolean checkPassword(int userId,String password) {
        User user = getUserById(userId);
        Assert.notNull(user,"userId不存在["+userId+"]");
        return passwordEncoder.matches(password,user.getPassword());
    }
}

