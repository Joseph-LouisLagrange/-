package com.alpha.classpie.pojo.file;

import lombok.Data;

@Data
public class SubmitTaskFile {
    private Integer userId;

    private Integer taskId;

    private Integer fileId;

    FilePlan filePlan;

    public SubmitTaskFile(Integer userId, Integer taskId, Integer fileId,FilePlan filePlan) {
        this.userId = userId;
        this.taskId = taskId;
        this.fileId = fileId;
        this.filePlan=filePlan;
    }

    public SubmitTaskFile(Integer userId, Integer taskId, Integer fileId) {
        this.userId = userId;
        this.taskId = taskId;
        this.fileId = fileId;
    }

    public SubmitTaskFile() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
