package com.alpha.classpie.pojo.user;

public class Permission {
    private Integer code;

    private String name;

    private String description;

    public Permission(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Permission() {
        super();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
