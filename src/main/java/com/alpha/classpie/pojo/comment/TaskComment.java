package com.alpha.classpie.pojo.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskComment extends Comment{
    private Integer taskId;


    public TaskComment(Integer taskId, Integer id) {
        this.taskId = taskId;
        this.id=id;
    }

    public TaskComment(Integer taskId, Integer id,Comment comment) {
        this.taskId = taskId;
        this.id=id;
        BeanUtils.copyProperties(comment,this);
    }

    public TaskComment(Integer taskId,Comment comment) {
        this.taskId = taskId;
        BeanUtils.copyProperties(comment,this);
    }

    public TaskComment() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCommentId() {
        return getId();
    }

}
