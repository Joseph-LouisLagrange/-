package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.SubmitTaskFile;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.pojo.task.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class SubmitTaskStudentViewWrapper extends SubmitTask {
    List<SubmitTaskFile> submitTaskFiles;
    //Task task;
}
