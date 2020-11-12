package com.alpha.classpie.pojo.file;

public class UserFile {
    private Integer id;

    private String name;

    private String isFolder;

    private Integer superiorFolderId;

    private String type;

    private Integer fileId;

    public UserFile(Integer id, String name, String isFolder, Integer superiorFolderId, String type, Integer fileId) {
        this.id = id;
        this.name = name;
        this.isFolder = isFolder;
        this.superiorFolderId = superiorFolderId;
        this.type = type;
        this.fileId = fileId;
    }

    public UserFile() {
        super();
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

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }

    public Integer getSuperiorFolderId() {
        return superiorFolderId;
    }

    public void setSuperiorFolderId(Integer superiorFolderId) {
        this.superiorFolderId = superiorFolderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
