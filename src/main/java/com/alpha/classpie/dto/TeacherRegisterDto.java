package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.user.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherRegisterDto extends RegisterDto<Integer> {
    protected Teacher teacher;
}
