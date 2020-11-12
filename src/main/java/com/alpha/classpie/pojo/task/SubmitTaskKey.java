package com.alpha.classpie.pojo.task;

public class SubmitTaskKey {
    private Integer userId;

    private Integer taskId;

    public SubmitTaskKey(Integer userId, Integer taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public SubmitTaskKey() {
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
