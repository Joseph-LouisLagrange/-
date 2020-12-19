package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.other.Major;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentTree extends WeakenedDepartment {
    protected List<WeakenedMajor> majors;
}
