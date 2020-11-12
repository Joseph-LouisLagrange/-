package com.alpha.classpie.pojo.user;

public class Group {
    private Integer id;

    private Integer courseId;

    private Integer maxUserCount;

    private String name;

    private Integer leaderId;

    public Group(Integer id, Integer courseId, Integer maxUserCount, String name, Integer leaderId) {
        this.id = id;
        this.courseId = courseId;
        this.maxUserCount = maxUserCount;
        this.name = name;
        this.leaderId = leaderId;
    }

    public Group() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(Integer maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }
}
