package com.alpha.classpie.pojo.other;

import com.alpha.classpie.dto.WeakenedDepartment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
public class Department extends WeakenedDepartment {


    EducationLevel educationLevel;

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
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
