package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.other.EducationLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EducationTree extends EducationLevel {
    List<DepartmentTree> departments;
}
