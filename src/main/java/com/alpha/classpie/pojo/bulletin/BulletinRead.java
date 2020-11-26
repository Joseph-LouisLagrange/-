package com.alpha.classpie.pojo.bulletin;

public class BulletinRead {
    private Integer bulletinId;

    private Integer userId;

    public BulletinRead(Integer bulletinId, Integer userId) {
        this.bulletinId = bulletinId;
        this.userId = userId;
    }

    public BulletinRead() {
        super();
    }

    public Integer getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(Integer bulletinId) {
        this.bulletinId = bulletinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
