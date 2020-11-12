package com.alpha.classpie.pojo.file;

public class ReleaseTaskFile {
    private Integer taskId;

    private Integer uploader;

    private Integer fileId;

    public ReleaseTaskFile(Integer taskId, Integer uploader, Integer fileId) {
        this.taskId = taskId;
        this.uploader = uploader;
        this.fileId = fileId;
    }

    public ReleaseTaskFile() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUploader() {
        return uploader;
    }

    public void setUploader(Integer uploader) {
        this.uploader = uploader;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
