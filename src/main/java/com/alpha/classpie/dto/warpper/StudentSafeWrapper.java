package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.user.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@JsonIgnoreProperties({"username","authorities","","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled","password"})
public class StudentSafeWrapper extends Student {
    @Override
    public String toString() {
        return "StudentSafeWrapper{" +
                "studentId='" + studentId + '\'' +
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
