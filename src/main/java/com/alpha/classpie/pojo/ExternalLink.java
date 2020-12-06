package com.alpha.classpie.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ExternalLink {
    private Integer id;

    private String title;

    private String link;

    private Integer courseId;

    private Date datetime;

    public ExternalLink(Integer id, String title, String link, Integer courseId, Date datetime) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.courseId = courseId;
        this.datetime = datetime;
    }

    public ExternalLink() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
