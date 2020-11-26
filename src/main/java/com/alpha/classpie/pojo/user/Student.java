package com.alpha.classpie.pojo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {

    protected String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Student(User user,String studentId){
        BeanUtils.copyProperties(user,this);
        setStudentId(studentId);
    }

    public Student(Integer userId,String studentId,User user){
        BeanUtils.copyProperties(user,this);
        setStudentId(studentId);
        setId(userId);
    }

    public Student(Integer userId,String studentId){
        this.setStudentId(studentId);
        this.setId(userId);
    }


    public Student(){}

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", emailNumber='" + emailNumber + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
