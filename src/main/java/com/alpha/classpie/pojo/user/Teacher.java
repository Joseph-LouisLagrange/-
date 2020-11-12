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
    List<TeacherCustomMajor> teacherCustomMajors=null;
    List<Department> teacherDepartments=null;
    List<Major> majors=null;
    List<TeacherTeachCourse> teacherTeachCourses =null;
}
