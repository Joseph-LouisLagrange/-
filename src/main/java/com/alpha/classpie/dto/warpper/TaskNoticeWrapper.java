package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.task.TaskNotice;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 杨能
 * @create 2020/12/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskNoticeWrapper extends TaskNotice {
    Task task;
}
