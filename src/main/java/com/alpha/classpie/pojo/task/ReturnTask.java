package com.alpha.classpie.pojo.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 打回作业
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReturnTask extends ReturnTaskKey {
    private Integer count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd",timezone = "GMT+8")
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
