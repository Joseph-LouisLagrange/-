package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.ReleaseTaskFile;
import com.alpha.classpie.pojo.task.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentViewTaskWrapper extends Task {
    List<ReleaseTaskFile> releaseTaskFiles;
    long commentCount=-1;
    boolean isSubmit;
    long expediteCount=0;//被催交次数
}
