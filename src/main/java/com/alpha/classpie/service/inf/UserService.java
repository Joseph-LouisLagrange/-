package com.alpha.classpie.service.inf;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.type.UserNameType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Service
public interface UserService<T extends User>{
    public T smsRegister(T user, int verificationCode) throws ExceptionDto;
    public T emailRegister(T user,int verificationCode);
    public T loginByAccountNumber(String accountNumber, String password);
    public T loginByEmailNumber(String emailNumber,String password);
    public T loginByTelephoneNumber(String telephoneNumber,String password);
    public T loginBySMSVerificationCode(String telephoneNumber, int verificationCode) throws ExceptionDto;
    public T login(UserNameType userNameType, String userName, String password);
    public T getUserById(int id);
    public User getUserByUserName(String username);
    public Set<Role> getRolesByUserId(int userId);
    public int getUserIdByUsername(String username);
    public boolean checkPassword(int userId,String password);
}
