package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.other.Department;
import lombok.Data;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Data
public class WeakenedMajor {
    protected Integer id;

    protected String name;

    public WeakenedMajor(Integer id, String name) {
        this.id = id;
        this.name = name;

    }
    public WeakenedMajor() {
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
