package com.alpha.classpie.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/21
 */
@Data
public class CourseSortDto {
    List<Integer> courseIds;
    List<Integer> courseOrder;
}
