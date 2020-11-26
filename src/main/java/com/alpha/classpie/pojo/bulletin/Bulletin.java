package com.alpha.classpie.pojo.bulletin;

import lombok.Data;

import java.util.Date;

@Data
public class Bulletin {
    private Integer id;

    private String name;

    private String resume;

    private Integer publisherId;

    private Integer courseId;

    private Date datetime;

    public Bulletin(Integer id, String name, String resume, Integer publisherId, Integer courseId, Date datetime) {
        this.id = id;
        this.name = name;
        this.resume = resume;
        this.publisherId = publisherId;
        this.courseId = courseId;
        this.datetime = datetime;
    }

    public Bulletin() {
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
