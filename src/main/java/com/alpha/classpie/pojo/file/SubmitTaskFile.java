package com.alpha.classpie.pojo.file;

public class SubmitTaskFile extends SubmitTaskFileKey {
    private Integer fileId;

    public SubmitTaskFile(Integer userId, Integer taskId, Integer fileId) {
        super(userId, taskId);
        this.fileId = fileId;
    }

    public SubmitTaskFile() {
        super();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
