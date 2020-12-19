package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseWrapper extends Course {
    long memberCount=-1;
    long interactionCount=-1;
    long taskCount=-1;
    long testCount=-1;
    int courseOrder;
    private Role role;
    List<Task> recentTasks=null;
    private String businessCard;
    public Boolean isArchiveAll=null;

    @Override
    public String toString() {
        return "CourseWrapper{" +
                "memberCount=" + memberCount +
                ", interactionCount=" + interactionCount +
                ", taskCount=" + taskCount +
                ", testCount=" + testCount +
                ", courseOrder=" + courseOrder +
                ", recentTasks=" + recentTasks +
                ", businessCard='" + businessCard + '\'' +
                '}';
    }
}
