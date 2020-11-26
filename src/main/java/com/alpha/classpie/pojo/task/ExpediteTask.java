package com.alpha.classpie.pojo.task;

/**
 * 催促作业
 */
public class ExpediteTask extends ExpediteTaskKey {
    private Integer count;

    public ExpediteTask(Integer taskId, Integer userId, Integer count) {
        super(taskId, userId);
        this.count = count;
    }

    public ExpediteTask() {
        super();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
