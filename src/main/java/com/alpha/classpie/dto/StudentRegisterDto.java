package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;


@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentRegisterDto extends RegisterDto<Integer> {
    private Student student;

    @Override
    public String toString() {
        return "StudentRegisterDto{" +
                "student=" + student +
                ", captcha=" + captcha +
                ", virtualId='" + virtualId + '\'' +
                ", registerUsername='" + registerUsername + '\'' +
                '}';
    }
}
