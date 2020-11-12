package com.alpha.classpie.pojo.other;

import com.alpha.classpie.dto.WeakenedMajor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;




@Data
@EqualsAndHashCode(callSuper = true)
public class Major extends WeakenedMajor {

    private Department department;

    public Major(Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    public Major() {
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
