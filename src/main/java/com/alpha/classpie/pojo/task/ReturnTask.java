package com.alpha.classpie.pojo.task;

import java.util.Date;

public class ReturnTask extends ReturnTaskKey {
    private Integer count;

    private Date lastTime;

    public ReturnTask(Integer userId, Integer taskId, Integer count, Date lastTime) {
        super(userId, taskId);
        this.count = count;
        this.lastTime = lastTime;
    }

    public ReturnTask() {
        super();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
