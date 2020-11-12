package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentRegisterDto extends RegisterDto<Integer> {
    private Student student;
}
