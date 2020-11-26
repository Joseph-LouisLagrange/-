package com.alpha.classpie.pojo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class Role implements Serializable {
    public static final Role STUDENT=new Role(1,"学生");
    public static final Role TEACHER=new Role(2,"老师");
    public static final Role ADMIN=new Role(4,"管理员");
    public static final Role GUEST=new Role(5,"游客");
    public static final Role TEACHING_ASSISTANT=new Role(6,"助教");
    private Integer id;

    private String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
