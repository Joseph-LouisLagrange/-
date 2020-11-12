package com.alpha.classpie.pojo.task;

public class ExpediteTaskKey {
    private Integer taskId;

    private Integer userId;

    public ExpediteTaskKey(Integer taskId, Integer userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    public ExpediteTaskKey() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
