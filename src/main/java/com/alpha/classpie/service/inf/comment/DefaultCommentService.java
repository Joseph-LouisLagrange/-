package com.alpha.classpie.service.inf.comment;

import com.alpha.classpie.dao.CommentMapper;
import com.alpha.classpie.dao.CommentReplyMapper;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.CommentExample;
import com.alpha.classpie.example.CommentReplyExample;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.CommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service("defaultCommentService")
@Transactional
public class DefaultCommentService implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentReplyMapper commentReplyMapper;

    protected boolean isBelong(int userId,int commentId){
        return commentMapper.isExist(userId,commentId);
    }

    protected void checkBelong(int userId,int commentId){
        if(!isBelong(userId, commentId)){
            throw new ExceptionDto("异常访问","对该评论无删除权限","重新登陆去获取权限");
        }
    }

    @Override
    public Comment updateComment(Comment comment, int commentId) {
        comment.setId(commentId);
        comment.setDatetime(new Date());
        commentMapper.updateByPrimaryKeyWithBLOBs(comment);
        return comment;
    }

    @Override
    public CommentReply updateCommReply(CommentReply commentReply, int userId, int commentReplyId) {
        commentReply.setId(commentReplyId);
        commentReply.setUserId(userId);
        commentReply.setDatetime(new Date());
        commentReplyMapper.updateByPrimaryKeySelective(commentReply);
        return commentReply;
    }

    @Override
    public boolean deleteComment(int commentId) {
        return commentMapper.deleteByPrimaryKey(commentId)>0;
    }

    @Override
    public Comment addComment(Comment comment) {
        comment.setDatetime(new Date());
        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public List<Comment> getComment(List<Integer> ids) {
        if(ids.isEmpty()) return Collections.emptyList();
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("datetime DESC");
        commentExample.createCriteria().andIdIn(ids);
        return commentMapper.selectByExampleWithBLOBs(commentExample);
    }

    @Override
    public CommentReply addCommentReply(CommentReply commentReply, int userId) {
        commentReply.setId(null);
        commentReply.setDatetime(new Date());
        commentReply.setUserId(userId);
        commentReplyMapper.insert(commentReply);
        return commentReply;
    }

    @Override
    public boolean deleteCommentReply(int commentReplyId) {
        return commentReplyMapper.deleteByPrimaryKey(commentReplyId)>0;
    }

    @Override
    public List<CommentReply> getCommentReply(int commentId) {
        CommentReplyExample commentReplyExample = new CommentReplyExample();
        commentReplyExample.setOrderByClause("datetime DESC");
        commentReplyExample.createCriteria().andCommentIdEqualTo(commentId);
        return commentReplyMapper.selectByExampleWithBLOBs(commentReplyExample);
    }

    @Override
    public List<Comment> list(CommentExample example) {
        return commentMapper.selectByExample(example);
    }
}
