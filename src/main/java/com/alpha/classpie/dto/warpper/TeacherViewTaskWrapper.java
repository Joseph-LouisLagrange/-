package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.ReleaseTaskFile;
import com.alpha.classpie.pojo.task.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherViewTaskWrapper extends Task {
    List<ReleaseTaskFile> releaseTaskFiles;

    long correctedCount=-1;

    long unpaidCount=-1;

    long notCorrectedCount=-1;

    long commentCount=-1;
}
