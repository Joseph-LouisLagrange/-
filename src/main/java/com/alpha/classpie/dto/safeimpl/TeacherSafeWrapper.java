package com.alpha.classpie.dto.safeimpl;

import com.alpha.classpie.pojo.user.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.BeanUtils;

/**
 * @author 杨能
 * @create 2020/11/4
 */
@JsonIgnoreProperties("password")
public class TeacherSafeWrapper extends Teacher {

}
