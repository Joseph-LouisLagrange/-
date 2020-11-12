package com.alpha.classpie.pojo.user;

public class TeacherMajor {
    private Integer userId;

    private Integer majorId;

    public TeacherMajor(Integer userId, Integer majorId) {
        this.userId = userId;
        this.majorId = majorId;
    }

    public TeacherMajor() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
