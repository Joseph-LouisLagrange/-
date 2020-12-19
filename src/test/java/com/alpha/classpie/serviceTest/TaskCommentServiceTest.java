package com.alpha.classpie.serviceTest;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.pojo.comment.TaskComment;
import com.alpha.classpie.service.inf.comment.TaskCommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class TaskCommentServiceTest {

    @Autowired
    TaskCommentService taskCommentService;

    @Autowired
    DataWrapper dataWrapper;

    @ParameterizedTest
    @ValueSource(ints = 10)
    public void addTaskCommentTest(int userId){
        taskCommentService.addTaskComment(new TaskComment(5,new Comment(10,new Date(),"垃圾作业895")), userId);
    }

    @ParameterizedTest
    @ValueSource(ints = {5})
    public void getCommentsTest(int taskId){

    }

    @ParameterizedTest
    @CsvSource("1,1,10")
    public void addCommentReplyTest(Integer commentId, Integer userId, Integer replyToId){
        CommentReply commentReply=new CommentReply(0,commentId,userId,new Date(),null,"反思了");
        Assertions.assertNotNull(taskCommentService.addCommentReply(commentReply, userId));
    }
}
