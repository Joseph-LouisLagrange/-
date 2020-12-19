package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.other.EducationLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
public class EducationTree extends EducationLevel {
    List<DepartmentTree> departments;
}
