package com.alpha.classpie.pojo.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BulletinComment extends Comment {
    private Integer bulletinId;


    public BulletinComment(Integer bulletinId,Comment comment) {
        this.bulletinId = bulletinId;
        BeanUtils.copyProperties(comment,this);
    }

    public BulletinComment(Integer id, Integer userId, Date datetime, String content, Integer bulletinId) {
        super(id, userId, datetime, content);
        this.bulletinId = bulletinId;
    }

    public BulletinComment() {
        super();
    }

    public Integer getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(Integer bulletinId) {
        this.bulletinId = bulletinId;
    }

    public Integer getCommentId() {
        return this.getId();
    }

}
