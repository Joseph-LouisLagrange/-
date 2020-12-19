package com.alpha.classpie.dto;

import lombok.Data;

import java.util.List;


@Data
public class CourseSortDto {
    List<Integer> courseIds;
    List<Integer> courseOrder;
}
