package com.alpha.classpie.pojo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {

    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

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
