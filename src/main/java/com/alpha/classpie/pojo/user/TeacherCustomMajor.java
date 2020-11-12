package com.alpha.classpie.pojo.user;

public class TeacherCustomMajor {
    private Integer userId;

    private String customMajorName;

    public TeacherCustomMajor(Integer userId, String customMajorName) {
        this.userId = userId;
        this.customMajorName = customMajorName;
    }

    public TeacherCustomMajor() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomMajorName() {
        return customMajorName;
    }

    public void setCustomMajorName(String customMajorName) {
        this.customMajorName = customMajorName;
    }
}
