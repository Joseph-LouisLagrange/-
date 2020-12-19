package com.alpha.classpie.serviceTest;

import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.service.inf.comment.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.PrintStream;
import java.util.Date;


@SpringBootTest
public class CommentServiceTest {

    @Resource(name = "defaultCommentService")
    CommentService commentService;

    @ParameterizedTest
    @CsvSource("16,1,-1,型的回复")
    public void addCommentReplyTest(Integer commentId, Integer userId, Integer replyToId, String content){
        CommentReply commentReply = commentService.addCommentReply(new CommentReply(null, commentId, null, null, null, content), userId);
        Assertions.assertNotNull(commentReply);
    }

    @ParameterizedTest
    @ValueSource(ints = {8})
    public void  deleteCommentReplyTest(int id){
        boolean b = commentService.deleteCommentReply(id);
        Assertions.assertTrue(b);

    }

    @ParameterizedTest
    @CsvSource("7,1,-1,型fff的回复")
    public void updateCommReplyTest(int commentReplyId, Integer userId, Integer replyToId, String content){
        CommentReply commentReply = commentService.updateCommReply(new CommentReply(commentReplyId, null, null, null, null, content), userId, commentReplyId);
        Assertions.assertNotNull(commentReply);
        System.out.println(commentReply);
    }
}
