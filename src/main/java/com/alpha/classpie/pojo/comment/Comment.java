package com.alpha.classpie.pojo.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    protected Integer id;

    protected Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date datetime;

    protected String content;

    public Comment(Integer id, Integer userId, Date datetime, String content) {
        this.id = id;
        this.userId = userId;
        this.datetime = datetime;
        this.content = content;
    }

    public Comment(Integer userId, Date datetime, String content) {
        this.userId = userId;
        this.datetime = datetime;
        this.content = content;
    }

    public Comment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
