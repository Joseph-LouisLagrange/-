package com.alpha.classpie.pojo;

public class UserCourseKey {
    private Integer userId;

    private Integer courseId;

    public UserCourseKey(Integer userId, Integer courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public UserCourseKey() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}