package com.alpha.classpie.pojo.user;

import com.alpha.classpie.pojo.other.Department;
import com.alpha.classpie.pojo.other.Major;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 杨能
 * @create 2020/11/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User {
    protected List<TeacherCustomMajor> teacherCustomMajors=null;
    protected List<Department> teacherDepartments=null;
    protected List<Major> majors=null;
    protected List<TeacherTeachCourse> teacherTeachCourses =null;

    @Override
    public String toString() {
        return "Teacher{" +
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
