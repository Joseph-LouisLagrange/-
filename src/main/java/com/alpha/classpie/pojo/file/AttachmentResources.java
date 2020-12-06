package com.alpha.classpie.pojo.file;

import lombok.Data;

@Data
public class AttachmentResources {
    private Integer id;

    private Long downloadCount;

    private Integer courseId;

    private Integer userFileId;

    private UserFile userFile;

    public AttachmentResources(Integer id, Long downloadCount, Integer courseId, Integer userFileId) {
        this.id = id;
        this.downloadCount = downloadCount;
        this.courseId = courseId;
        this.userFileId = userFileId;
    }

    public AttachmentResources(Integer courseId, Integer userFileId) {
        this.courseId = courseId;
        this.userFileId = userFileId;
        this.downloadCount=0L;
    }

    public AttachmentResources() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserFileId() {
        return userFileId;
    }

    public void setUserFileId(Integer userFileId) {
        this.userFileId = userFileId;
    }
}
