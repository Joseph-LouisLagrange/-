package com.alpha.classpie.pojo.task;

import com.alpha.classpie.pojo.Notice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * 作业通知
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskNotice extends Notice{
    public static final String releaseType="发布";
    public static final String recallType="打回";
    public static final String correctType="批阅";
    public static final String expediteType="催交";
    @JsonIgnore
    private Integer noticeId;

    private Integer taskId;

    private String type;

    public TaskNotice(Integer noticeId, Integer taskId, String type) {
        this.noticeId = noticeId;
        this.taskId = taskId;
        this.type = type;
    }

    public TaskNotice(Integer noticeId, Integer taskId, String type,Notice notice) {
        this.noticeId = noticeId;
        this.taskId = taskId;
        this.type = type;
        BeanUtils.copyProperties(notice,this);
    }




    public TaskNotice() {
        super();
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
