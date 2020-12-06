package com.alpha.classpie.pojo.file;

public class ReleaseTaskFileKey {
    private Integer taskId;

    private Integer fileId;

    public ReleaseTaskFileKey(Integer taskId, Integer fileId) {
        this.taskId = taskId;
        this.fileId = fileId;
    }

    public ReleaseTaskFileKey() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
