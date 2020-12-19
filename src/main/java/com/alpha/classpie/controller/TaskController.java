package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.service.inf.CourseService;
import com.alpha.classpie.service.inf.TaskService;
import com.alpha.classpie.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource(name = "defaultTaskService")
    TaskService taskService;

    @Autowired
    DataWrapper dataWrapper;

    @Autowired
    CourseService courseService;


    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/getReleasedTasks")
    public List<Task> getReleasedTasks(@RequestParam(name = "courseId") int courseId){
        Role roleInCourse = courseService.getRoleInCourse(courseId, UserController.getUserId());
        if(roleInCourse==null) return null;
        List<Task> releasedTasks = taskService.getReleasedTasks(courseId);
        if(roleInCourse.equals(Role.TEACHER)){
            return releasedTasks.stream().map(dataWrapper::doTeacherViewTaskWrap).collect(Collectors.toList());
        }
        if(roleInCourse.equals(Role.STUDENT)){
            return releasedTasks.stream().map(task -> dataWrapper.doStudentViewTaskWrap(task,UserController.getUserId())).collect(Collectors.toList());
        }
            return null;
        }

    @RequestMapping("/releaseTask")
    public Task releaseTask(@RequestParam(name = "task") String jsonTask
            , @RequestParam(value = "file",required = false) MultipartFile[] multipartFiles
    ) throws IOException {
        if(multipartFiles==null){
            multipartFiles=new MultipartFile[0];//来一个空的文件序列
        }
        Task task = toTask(jsonTask);//手动转
        Task result=null;
        if(task.getIsCheckDuplicate()){
            result = taskService.releaseDoCheckDuplicateTask(task,multipartFiles,UserController.getUserId());
        }else {
            result=taskService.releaseNoCheckDuplicateTask(task,multipartFiles,UserController.getUserId());
        }
        return dataWrapper.doTeacherViewTaskWrap(result);
    }

    @RequestMapping("/simpleEditTask")
    public Task simpleEditTask(@RequestBody Task task){
       return dataWrapper.doTeacherViewTaskWrap(taskService.simpleEditTask(task,task.getId()));
    }

    @RequestMapping("/powerEditTask")
    public Task powerEditTask(@RequestParam(name = "task")String jsonTask
            ,@RequestParam(value = "file",required = false) MultipartFile[] addNewMultipartFiles
            ,@RequestParam(name = "deleteReleasedTaskFileId",required = false,defaultValue = "[]")String jsonDeleteReleasedTaskFileId) throws IOException {
        Task task = toTask(jsonTask);
        if(addNewMultipartFiles==null){
            addNewMultipartFiles=new MultipartFile[0];
        }
        List<Integer> deleteReleasedTaskFileId=new ArrayList<>();
        if(jsonDeleteReleasedTaskFileId!=null){
            deleteReleasedTaskFileId=JsonUtil.fromJson(jsonDeleteReleasedTaskFileId,List.class,Integer.class);
        }
        return dataWrapper.doTeacherViewTaskWrap(taskService.powerEditTask(task,task.getId(),addNewMultipartFiles,deleteReleasedTaskFileId));
    }

    @RequestMapping("/getUnpaidStudents")
    public List<Student> getUnpaidStudents(@RequestParam(name = "taskId") int taskId){
        return taskService.getUnpaidStudents(taskId).stream().map(dataWrapper::doStudentSafeWrap).map(studentSafeWrapper -> dataWrapper.doUnpaidTaskStudentWrap(taskId,studentSafeWrapper)).collect(Collectors.toList());
    }

    @RequestMapping("/deleteTask")
    public boolean deleteTask(@RequestParam(name = "taskId") int taskId) throws IOException {
        return taskService.deleteTask(taskId);
    }

    @RequestMapping("/downloadTaskFile")
    public void downTaskFile(@RequestParam("fileId")int fileId, HttpServletResponse response) throws IOException {
        taskService.downTaskFile(fileId,response);
    }

    @RequestMapping("/expediteTask")
    public boolean expediteTask(@RequestParam("taskId") int taskId,@RequestParam("toUserId") int toUserId){
        return taskService.expediteTask(UserController.getUserId(),taskId,toUserId);
    }

    @RequestMapping("/batchExpediteTask")
    public boolean batchExpediteTask(@RequestParam("taskId")int taskId,@RequestParam("userIds")String jsonUserIds) throws JsonProcessingException {
        int[] userIds = JsonUtil.fromJson(jsonUserIds, int[].class);
         return taskService.batchExpediteTask(UserController.getUserId(),taskId,userIds);
    }

    private Task toTask(String jsonTask) throws JsonProcessingException {
        return JsonUtil.fromJson(jsonTask,Task.class);
    }

    @RequestMapping("/getTask")
    public Task getTask(@RequestParam("taskId")int taskId){
        if(!taskService.canUse(UserController.getUserId(),taskId)){
            return null;
        }
        if(UserController.getUser().getRoles().contains(Role.TEACHER)){
            //表示为老师，可以获取课程内的任一课程
           return dataWrapper.doTeacherViewTaskWrap(taskService.getTaskById(taskId));
        }else {
            return dataWrapper.doStudentViewTaskWrap(taskService.getTaskById(taskId),UserController.getUserId());
        }
    }
}
