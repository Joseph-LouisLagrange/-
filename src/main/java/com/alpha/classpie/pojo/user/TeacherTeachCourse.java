package com.alpha.classpie.pojo.user;

public class TeacherTeachCourse {
    private Integer userId;

    private String teachCourse;

    public TeacherTeachCourse(Integer userId, String teachCourse) {
        this.userId = userId;
        this.teachCourse = teachCourse;
    }

    public TeacherTeachCourse() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTeachCourse() {
        return teachCourse;
    }

    public void setTeachCourse(String teachCourse) {
        this.teachCourse = teachCourse;
    }
}
