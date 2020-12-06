package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.SubmitTaskFile;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubmitTaskTeacherViewWrapper extends SubmitTask {
     List<SubmitTaskFile> submitTaskFiles;
     //加入辅助的Task属性 【这是相当大的败笔！！！！！！！！！！】 【这是相当大的败笔！！！！！！！！！！】
     //Task task;
     Integer fullMarks;
     Student student;
}
