package com.alpha.classpie.pojo.course;

public class LessonPreparationArea {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer courseId;

    public LessonPreparationArea(Integer id, String name, Integer userId, Integer courseId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.courseId = courseId;
    }

    public LessonPreparationArea() {
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
