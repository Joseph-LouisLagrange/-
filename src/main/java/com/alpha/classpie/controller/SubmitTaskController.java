package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.dto.warpper.SubmitTaskTeacherViewWrapper;
import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.pojo.task.ReturnTask;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.FilePlanService;
import com.alpha.classpie.service.inf.SubmitTaskService;
import com.alpha.classpie.service.inf.TaskService;
import com.alpha.classpie.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jodconverter.core.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/submitTask")
public class SubmitTaskController {
    @Resource(name = "defaultSubmitTaskService")
    SubmitTaskService submitTaskService;

    @Autowired
    TaskService taskService;

    @Autowired
    DataWrapper dataWrapper;


    @RequestMapping("/submitTask")
    public SubmitTask submitTask(@RequestParam("/file") MultipartFile[] multipartFiles,@RequestParam("taskId") int taskId) throws IOException {
        return dataWrapper.doSubmitTaskStudentViewWrap(submitTaskService.submitTask(multipartFiles,taskId,UserController.getUserId()));
    }


    @RequestMapping("/getSubmittedTask")
    public SubmitTask getSubmittedTask(@RequestParam("submittedTaskId")int submittedTaskId,@RequestParam("userId")int userId){
        SubmitTaskTeacherViewWrapper submitTaskTeacherViewWrapper = dataWrapper.doSubmitTaskTeacherViewWrap((submitTaskService.getSubmitTask(submittedTaskId, userId)));
        //[败笔处 !!!!!!!!]
        submitTaskTeacherViewWrapper.setFullMarks(taskService.getTaskById(submittedTaskId).getFullMarks());
        return submitTaskTeacherViewWrapper;
    }

    @RequestMapping("/getMySubmittedTask")
    public SubmitTask getMySubmittedTask(@RequestParam("taskId") int taskId){
        return dataWrapper.doSubmitTaskStudentViewWrap(submitTaskService.getSubmitTask(taskId,UserController.getUserId()));
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/getSubmittedTasks")
    public List<SubmitTask> getSubmittedTasks(@RequestParam("taskId")int taskId){
        return submitTaskService.getSubmitTasks(taskId).stream().map(dataWrapper::doSubmitTaskTeacherViewWrap).collect(Collectors.toList());
    }

    @RequestMapping("/getSubmittedStudent")
    public List<User> getSubmittedStudent(@RequestParam("taskId")int taskId){
        return submitTaskService.getSubmittedStudent(taskId).stream().map(dataWrapper::doStudentSafeWrap).collect(Collectors.toList());
    }

    @RequestMapping("/correctTask")
    public boolean correctSubmittedTask(@RequestParam("submittedTaskId")int submittedTaskId
            ,@RequestParam("score") float score){
        return submitTaskService.correctSubmittedTask(UserController.getUserId(), submittedTaskId,UserController.getUserId(),score);
    }

    @RequestMapping("/getMyRecallSubmittedTasks")
    public List<ReturnTask> getMyRecallSubmittedTasks(@RequestParam(name = "taskId")int taskId){
        return null;
    }


    @RequestMapping("/updateSubmittedTask")
    public SubmitTask updateSubmittedTask(@RequestParam("submittedTaskId")int submittedTaskId,@RequestParam(name = "jsonDeleteFileIds") String jsonDeleteFileIds, @RequestParam(value = "file",required = false) MultipartFile[] addNewMultipartFile) throws IOException {
        List<Integer> deleteFileIds = JsonUtil.fromJson(jsonDeleteFileIds, List.class, Integer.TYPE);
        int userId = UserController.getUserId();
        if(submitTaskService.getSubmitTask(submittedTaskId,userId)==null){
            //表示没有提交过
            return submitTaskService.submitTask(addNewMultipartFile,submittedTaskId,userId);
        }
        return dataWrapper.doSubmitTaskStudentViewWrap(submitTaskService.updateSubmitTask(UserController.getUserId(),submittedTaskId,deleteFileIds,addNewMultipartFile));
    }

    @RequestMapping("/downAllSubmittedTask")
    public void downAllSubmittedTask(@RequestParam("taskId")int taskId, HttpServletResponse response) throws IOException {
        submitTaskService.downAllSubmittedTask(taskId,response);
    }

    @RequestMapping("/downSomeSubmittedTask")
    public void downSomeSubmittedTask(@RequestParam("taskId")int taskId,@RequestParam("userIds")Integer[] userIds, HttpServletResponse response) throws IOException {
        submitTaskService.downSomeSubmittedTask(taskId,userIds,response);
    }


    @RequestMapping("/downSubmittedTaskFile")
    public void downSubmittedTaskFile(@RequestParam("fileId")int fileId,HttpServletResponse response) throws IOException {
        submitTaskService.downSubmittedTaskFile(fileId,response);
    }

    @RequestMapping("/canUseFile")
    public boolean canUseFile(@RequestParam("taskId")int taskId,@RequestParam("fileId")int fileId){
        return submitTaskService.canUseFile(taskId,UserController.getUserId(),fileId);
    }

    @RequestMapping("/previewSubmittedTaskFile")
    public void previewSubmittedTaskFile(@RequestParam("fileId")int fileId,HttpServletResponse response) throws IOException, OfficeException {
        submitTaskService.previewSubmittedTaskFile(fileId, response);
    }

    @RequestMapping("/batchCorrectSubmittedTask")
    public boolean batchCorrectSubmittedTask(@RequestParam("userIds") String jsonUserIds,@RequestParam("submittedTaskId") int submittedTaskId,@RequestParam("score") float score) throws JsonProcessingException {
        Integer[] userIds = JsonUtil.fromJson(jsonUserIds, Integer[].class);
        return submitTaskService.batchCorrectSubmittedTask(userIds,submittedTaskId,UserController.getUserId(),score);
    }
}
