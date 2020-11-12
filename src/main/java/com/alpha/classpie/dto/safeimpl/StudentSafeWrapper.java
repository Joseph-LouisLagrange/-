package com.alpha.classpie.dto.safeimpl;

import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.pojo.user.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/5
 */

@JsonIgnoreProperties("password")
public class StudentSafeWrapper extends Student {

}
