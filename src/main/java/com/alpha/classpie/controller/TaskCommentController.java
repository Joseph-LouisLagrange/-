package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.pojo.comment.TaskComment;
import com.alpha.classpie.service.inf.comment.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/taskComment")
public class TaskCommentController {

    @Resource(name = "defaultTaskCommentService")
    TaskCommentService taskCommentService;

    @Autowired
    DataWrapper dataWrapper;

    @RequestMapping("/getTaskComments")
    public List<Comment> getTaskComments(@RequestParam("taskId")int taskId){
        return taskCommentService.getComments(taskId).stream().map(dataWrapper::doCommentWrapper).collect(Collectors.toList());
    }

    @RequestMapping("/deleteTaskComment")
    public boolean deleteTaskComment(@RequestParam("taskId")int taskId,@RequestParam("commentId")int commentId){
       return taskCommentService.deleteTaskComment(UserController.getUserId(), taskId, commentId);
    }

    @RequestMapping("/addTaskComment")
    public Comment addTaskComment(@RequestBody TaskComment taskComment){
        return dataWrapper.doCommentWrapper(taskCommentService.addTaskComment(taskComment, UserController.getUserId()));
    }

    @RequestMapping("/reEditTaskComment")
    public Comment editTaskComment(@RequestBody TaskComment taskComment){
        return dataWrapper.doCommentWrapper(taskCommentService.updateTaskComment(taskComment,UserController.getUserId(),taskComment.getId()));
    }
}
