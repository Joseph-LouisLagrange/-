package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.task.ReturnTask;
import com.alpha.classpie.service.inf.RecallTaskService;
import com.alpha.classpie.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/20
 */
@RestController
@RequestMapping("/recallSubmittedTask")
public class RecallSubmittedTaskController {
    @Resource(name = "defaultRecallTaskService")
    RecallTaskService recallSubmittedTaskService;
    @Autowired
    DataWrapper dataWrapper;
    @RequestMapping("/recallSubmittedTask")
    public boolean recallSubmittedTask(@RequestParam(name = "submittedTaskId")int submittedTaskId,@RequestParam(name = "userId")int userId) throws IOException {
        return recallSubmittedTaskService.returnTask(UserController.getUserId(), submittedTaskId,userId);
    }
    @RequestMapping("/getAllReturnTask")
    public List<ReturnTask> getAllReturnTask(@RequestParam(name = "submittedTaskId")int submittedTaskId){
        return recallSubmittedTaskService.getAllReturnTask(submittedTaskId).stream().map(dataWrapper::doReturnTaskWrap).collect(Collectors.toList());
    }
    @RequestMapping("/batchRecallSubmittedTask")
    public boolean batchRecallSubmittedTask(@RequestParam(name = "submittedTaskId")int submittedTaskId,@RequestParam(name = "userIds")String jsonUserIds) throws IOException {
        Integer[] userIds = JsonUtil.fromJson(jsonUserIds, Integer[].class);
        return recallSubmittedTaskService.batchReturnTask(UserController.getUserId(),submittedTaskId,userIds);
    }
}
