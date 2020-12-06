package com.alpha.classpie.pojo;

import lombok.Data;

@Data
public class Notice {
    protected Integer id;

    protected Integer userId;

    protected String title;

    protected Boolean isRead;

    protected String content;

    public Notice(Integer id, Integer userId, String title, Boolean isRead, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.isRead = isRead;
        this.content = content;
    }

    public Notice() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
