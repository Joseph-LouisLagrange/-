package com.alpha.classpie.pojo;

public class ExternalLink {
    private Integer id;

    private String title;

    private String link;

    private Integer areaId;

    public ExternalLink(Integer id, String title, String link, Integer areaId) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.areaId = areaId;
    }

    public ExternalLink() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}