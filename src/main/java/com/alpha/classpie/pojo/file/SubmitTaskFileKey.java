package com.alpha.classpie.pojo.file;

public class SubmitTaskFileKey {
    private Integer userId;

    private Integer taskId;

    public SubmitTaskFileKey(Integer userId, Integer taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public SubmitTaskFileKey() {
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
}
