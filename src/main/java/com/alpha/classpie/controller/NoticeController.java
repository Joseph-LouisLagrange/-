package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.task.TaskNotice;
import com.alpha.classpie.service.inf.notice.TaskNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/26
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource(name = "defaultTaskNoticeService")
    TaskNoticeService taskNoticeService;
    @Autowired
    DataWrapper dataWrapper;
    @RequestMapping("/getNoReadTaskNotices")
    public List<TaskNotice> getNoReadTaskNotices(){
        return taskNoticeService.getAllNoReadTaskNotice(UserController.getUserId()).stream().map(dataWrapper::doTaskNoticeWrap).collect(Collectors.toList());
    }

    @RequestMapping("/getAllTaskNotice")
    public List<TaskNotice> getAllTaskNotice(){
        return taskNoticeService.getAllTaskNotice(UserController.getUserId()).stream().map(dataWrapper::doTaskNoticeWrap).collect(Collectors.toList());
    }

    @RequestMapping("/markReadTaskNotice")
    public boolean markReadTaskNotice(@RequestParam("noticeId") int noticeId){
        return taskNoticeService.markReadTaskNotice(noticeId,UserController.getUserId());
    }
}
