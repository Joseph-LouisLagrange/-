package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties({"username","authorities","","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled","password"})
public class UserSafeWrapper extends User{

}
