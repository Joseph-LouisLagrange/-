package com.alpha.classpie.pojo.task;

import java.util.Date;

public class Task {
    private Integer id;

    private Integer courseId;

    private String name;

    private Date deadline;

    private Integer fullMarks;

    private Boolean isCheckDuplicate;

    private Integer promulgator;

    private String resume;

    public Task(Integer id, Integer courseId, String name, Date deadline, Integer fullMarks, Boolean isCheckDuplicate, Integer promulgator, String resume) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.deadline = deadline;
        this.fullMarks = fullMarks;
        this.isCheckDuplicate = isCheckDuplicate;
        this.promulgator = promulgator;
        this.resume = resume;
    }

    public Task() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(Integer fullMarks) {
        this.fullMarks = fullMarks;
    }

    public Boolean getIsCheckDuplicate() {
        return isCheckDuplicate;
    }

    public void setIsCheckDuplicate(Boolean isCheckDuplicate) {
        this.isCheckDuplicate = isCheckDuplicate;
    }

    public Integer getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(Integer promulgator) {
        this.promulgator = promulgator;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
