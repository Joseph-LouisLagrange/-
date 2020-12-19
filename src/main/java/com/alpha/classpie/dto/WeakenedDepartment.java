package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.other.Department;
import com.alpha.classpie.pojo.other.EducationLevel;
import com.alpha.classpie.pojo.other.Major;
import lombok.Data;


@Data
public class WeakenedDepartment  {
    protected Integer id;

    protected String name;

    public WeakenedDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    public WeakenedDepartment() {
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
