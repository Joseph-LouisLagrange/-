package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.user.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 杨能
 * @create 2020/11/4
 */
@JsonIgnoreProperties({"username","authorities","","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled","password"})
public class TeacherSafeWrapper extends Teacher {
    @Override
    public String toString() {
        return "TeacherSafeWrapper{" +
                "teacherCustomMajors=" + teacherCustomMajors +
                ", teacherDepartments=" + teacherDepartments +
                ", majors=" + majors +
                ", teacherTeachCourses=" + teacherTeachCourses +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", emailNumber='" + emailNumber + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
