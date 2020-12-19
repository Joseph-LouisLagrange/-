package com.alpha.classpie.service.inf.comment;

import com.alpha.classpie.example.CommentExample;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.service.inf.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentService extends BaseService<Comment, CommentExample>  {
    public boolean deleteComment(int commentId);
    public Comment addComment(Comment comment);
    public List<Comment> getComment(List<Integer> ids);
    public Comment updateComment(Comment comment,int commentId);

    //回复服务(作为辅助服务来实现)
    public CommentReply addCommentReply(CommentReply commentReply,int userId);
    public boolean deleteCommentReply(int commentReplyId);
    public List<CommentReply> getCommentReply(int commentId);
    public CommentReply updateCommReply(CommentReply commentReply,int userId,int commentReplyId);
}
