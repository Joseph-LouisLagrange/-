package com.alpha.classpie.pojo.file;

public class AreaUserFileKey {
    private Integer areaId;

    private Integer userFileId;

    public AreaUserFileKey(Integer areaId, Integer userFileId) {
        this.areaId = areaId;
        this.userFileId = userFileId;
    }

    public AreaUserFileKey() {
        super();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getUserFileId() {
        return userFileId;
    }

    public void setUserFileId(Integer userFileId) {
        this.userFileId = userFileId;
    }
}
