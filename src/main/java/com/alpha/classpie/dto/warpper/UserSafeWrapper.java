package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 杨能
 * @create 2020/11/2
 */

@JsonIgnoreProperties({"username","authorities","","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled","password"})
public class UserSafeWrapper extends User{

}
