package com.alpha.classpie.pojo.task;

public class TaskCheckDuplicate {
    private Integer taskId;

    private Integer warningValue;

    private String isReturn;

    private Integer returnValue;

    public TaskCheckDuplicate(Integer taskId, Integer warningValue, String isReturn, Integer returnValue) {
        this.taskId = taskId;
        this.warningValue = warningValue;
        this.isReturn = isReturn;
        this.returnValue = returnValue;
    }

    public TaskCheckDuplicate() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(Integer warningValue) {
        this.warningValue = warningValue;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public Integer getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Integer returnValue) {
        this.returnValue = returnValue;
    }
}
