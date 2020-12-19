package com.alpha.classpie.controller;

import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.service.inf.comment.CommentService;
import com.alpha.classpie.service.inf.comment.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/commentReply")
public class CommentReplyController {

    @Resource(name = "defaultCommentService")
    CommentService commentService;

    @RequestMapping("/reply")
    public CommentReply commentReply(@RequestBody CommentReply commentReply){
        return commentService.addCommentReply(commentReply, UserController.getUserId());
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam("commentReplyId") int commentReplyId){
        return commentService.deleteCommentReply(commentReplyId);
    }

    @RequestMapping("/reEdit")
    public CommentReply update(@RequestBody CommentReply commentReply){
        return commentService.updateCommReply(commentReply,UserController.getUserId(),commentReply.getId());
    }
}
