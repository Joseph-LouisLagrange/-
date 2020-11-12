package com.alpha.classpie.pojo.bulletin;

import java.util.Date;

public class BulletinComment extends BulletinCommentKey {
    private Date datetime;

    private String content;

    public BulletinComment(Integer bulletinId, Integer userId, Date datetime, String content) {
        super(bulletinId, userId);
        this.datetime = datetime;
        this.content = content;
    }

    public BulletinComment() {
        super();
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
