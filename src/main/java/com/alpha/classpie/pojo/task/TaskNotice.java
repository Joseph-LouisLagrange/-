package com.alpha.classpie.pojo.task;

public class TaskNotice {
    private Integer noticeId;

    private Integer taskId;

    private String type;

    public TaskNotice(Integer noticeId, Integer taskId, String type) {
        this.noticeId = noticeId;
        this.taskId = taskId;
        this.type = type;
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
