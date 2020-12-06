package com.alpha.classpie.util;

import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.CourseService;
import com.alpha.classpie.service.inf.notice.SendNoticeService;
import com.alpha.classpie.service.inf.TaskService;
import com.alpha.classpie.service.inf.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/21
 */
@Component
public class SendNoticeServiceHelper {

    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @Resource(name = "defaultUserService")
    UserService userService;

    @Resource(name = "defaultTaskService")
    TaskService taskService;

    public void sendTaskNotice(SendNoticeService sendNoticeService,User toUser,Course course,User teacher,Task task){
        if(toUser!=null&&toUser.getEmailNumber()!=null&&teacher!=null){
            sendNoticeService.sendNotice(toUser.getEmailNumber(),course.getName(),teacher.getName(),task.getName());
        }
    }

    public void sendTaskNotice(SendNoticeService sendNoticeService, int to, int courseId, int teacherId, int taskId){
        User toUser = userService.getUserById(to);
        User teacher=userService.getUserById(teacherId);
        Task task = taskService.getTaskById(taskId);
        Course course = courseService.getCourseById(courseId);
        sendTaskNotice(sendNoticeService,toUser,course,teacher,task);
    }

    public void sendTaskNotice(SendNoticeService sendNoticeService, int to, int teacherId, int taskId){
        Task taskById = taskService.getTaskById(taskId);
        if(taskById!=null){
            sendTaskNotice(sendNoticeService, to,taskById.getCourseId(),teacherId, taskId);
        }
    }


    public void sendTaskNotice(SendNoticeService sendNoticeService, List<Integer> tos, int courseId, int teacherId, int taskId){
        for(Integer to:tos){
            sendTaskNotice(sendNoticeService, to, courseId, teacherId, taskId);
        }
    }
}
