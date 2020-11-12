package com.alpha.classpie.pojo.file;

public class FilePlan {
    private Integer id;

    private String name;

    private String type;

    private String storagePath;

    public FilePlan(Integer id, String name, String type, String storagePath) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.storagePath = storagePath;
    }

    public FilePlan() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }
}
