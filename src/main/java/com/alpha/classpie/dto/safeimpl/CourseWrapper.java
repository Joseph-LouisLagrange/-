package com.alpha.classpie.dto.safeimpl;

import com.alpha.classpie.pojo.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 杨能
 * @create 2020/11/9
 * 包装器:加强属性方面
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseWrapper extends Course {
    long memberCount=-1;
    long interactionCount=-1;
    long taskCount=-1;
    long testCount=-1;
    int order;
}
