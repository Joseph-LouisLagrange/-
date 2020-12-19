package com.alpha.classpie.service.impl.safe;

import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.UserService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service("defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "defaultUserService")
    UserService userService;

    @Override
    //@Cacheable("userDetails")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUserName = userService.getUserByUsername(username);
        if(userByUserName==null){
            throw new UsernameNotFoundException("账号未找到");
        }
        return userByUserName;
    }
}
