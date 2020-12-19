package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.task.ReturnTask;
import com.alpha.classpie.pojo.user.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ReturnTaskWrapper extends ReturnTask {
    Student student;
}
