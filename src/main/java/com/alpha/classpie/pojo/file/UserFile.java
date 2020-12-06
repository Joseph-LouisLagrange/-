package com.alpha.classpie.pojo.file;

import lombok.Data;

import java.util.Date;

@Data
public class UserFile {
    private Integer id;

    private String name;

    private Boolean isFolder;

    private Integer superiorFolderId;

    private Integer fileId;

    FilePlan filePlan;

    Date datetime;

    public UserFile(Integer id, String name, Boolean isFolder, Integer superiorFolderId, Integer fileId,Date datetime) {
        this.id = id;
        this.datetime=datetime;
        this.name = name;
        this.isFolder = isFolder;
        this.superiorFolderId = superiorFolderId;
        this.fileId = fileId;
        this.datetime=new Date();
    }


    public UserFile(String name, Boolean isFolder, Integer superiorFolderId, Integer fileId) {
        this.name = name;
        this.isFolder = isFolder;
        this.superiorFolderId = superiorFolderId;
        this.fileId = fileId;
        this.datetime=new Date();
    }

    public UserFile() {
        super();
    }

    public UserFile(Integer id) {
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    public Integer getSuperiorFolderId() {
        return superiorFolderId;
    }

    public void setSuperiorFolderId(Integer superiorFolderId) {
        this.superiorFolderId = superiorFolderId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "id="+this.id +
                ",name='" + name + '\'' +
                ", isFolder=" + isFolder;
    }
}
