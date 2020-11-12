package com.alpha.classpie.pojo.other;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EducationLevel {
    public Integer id;

    public String name;

    public EducationLevel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public EducationLevel() {
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
}
