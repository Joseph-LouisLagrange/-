package com.alpha.classpie.pojo.user;

public class TeacherDepartment {
    private Integer userId;

    private Integer departmentId;

    public TeacherDepartment(Integer userId, Integer departmentId) {
        this.userId = userId;
        this.departmentId = departmentId;
    }

    public TeacherDepartment() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
