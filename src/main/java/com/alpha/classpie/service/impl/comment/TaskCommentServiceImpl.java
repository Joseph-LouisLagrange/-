package com.alpha.classpie.service.impl.comment;

import com.alpha.classpie.dao.TaskCommentMapper;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.TaskCommentExample;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.TaskComment;
import com.alpha.classpie.service.inf.TaskService;
import com.alpha.classpie.service.inf.comment.DefaultCommentService;
import com.alpha.classpie.service.inf.comment.TaskCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/22
 */
@Service("defaultTaskCommentService")
@Transactional
public class TaskCommentServiceImpl extends DefaultCommentService implements TaskCommentService {
    @Autowired
    TaskCommentMapper taskCommentMapper;

    @Autowired
    TaskService taskService;

    public void check1(int taskId, int commentId){
        if(!taskCommentMapper.isExist(new TaskComment(taskId,commentId))){
            throw new ExceptionDto("异常数据操作","评论已被不存在或者已被删除","刷新页面或者重新登陆");
        }
    }

    public void check2(int userId,int taskId){
        if(!taskService.canUse(userId,taskId)){
            throw new ExceptionDto("访问异常","您对该课程不具备评价权限","刷新页面或者重新登录");
        }
    }

    @Override
    public Comment addTaskComment(TaskComment taskComment, int userId) {
        taskComment.setUserId(userId);
        BeanUtils.copyProperties(super.addComment(taskComment),taskComment);
        taskCommentMapper.insert(taskComment);
        return taskComment;
    }

    @Override
    public List<TaskComment> getTaskComments(int taskId) {
        return getComments(taskId).stream().map(comment -> new TaskComment(taskId,comment)).collect(Collectors.toList());
    }

    @Override
    public boolean deleteTaskComment(int userId, int taskId, int commentId) {
        check1(taskId, commentId);
        check2(userId, taskId);
        checkBelong(userId, commentId);
        return super.deleteComment(commentId);
    }

    @Override
    public List<Comment> getComments(int taskId) {
        TaskCommentExample taskCommentExample = new TaskCommentExample();
        taskCommentExample.createCriteria().andTaskIdEqualTo(taskId);
        List<Integer> commentIds = taskCommentMapper.selectByExample(taskCommentExample).stream().map(TaskComment::getCommentId).collect(Collectors.toList());
        return super.getComment(commentIds);
    }

    @Override
    public Comment updateTaskComment(TaskComment taskComment, int userId, int commentId) {
        checkBelong(userId, commentId);
        taskComment.setUserId(userId);
        return super.updateComment(taskComment,commentId);
    }

    @Override
    public long getTaskCommentCount(int taskId) {
        TaskCommentExample taskCommentExample = new TaskCommentExample();
        taskCommentExample.createCriteria().andTaskIdEqualTo(taskId);
        return taskCommentMapper.countByExample(taskCommentExample);
    }
}
