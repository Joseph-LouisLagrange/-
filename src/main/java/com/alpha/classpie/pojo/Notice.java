package com.alpha.classpie.pojo;

public class Notice {
    private Integer id;

    private Integer userId;

    private String title;

    private String mark;

    private String content;

    public Notice(Integer id, Integer userId, String title, String mark, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.mark = mark;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}