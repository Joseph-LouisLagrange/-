package com.alpha.classpie.pojo.file;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReleaseTaskFile extends ReleaseTaskFileKey {
    private Integer uploader;

    FilePlan filePlan;

    public ReleaseTaskFile(Integer taskId, Integer fileId, Integer uploader, FilePlan filePlan) {
        super(taskId, fileId);
        this.uploader = uploader;
        this.filePlan = filePlan;
    }

    public ReleaseTaskFile(Integer taskId, Integer fileId, Integer uploader) {
        super(taskId, fileId);
        this.uploader = uploader;
    }

    public ReleaseTaskFile() {
        super();
    }

    public Integer getUploader() {
        return uploader;
    }

    public void setUploader(Integer uploader) {
        this.uploader = uploader;
    }
}
