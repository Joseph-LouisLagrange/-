package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.user.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherRegisterDto extends RegisterDto<Integer> {
    protected Teacher teacher;
}
