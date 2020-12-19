package com.alpha.classpie.service.inf.comment;

import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.TaskComment;

import java.util.List;


public interface TaskCommentService extends CommentService {
    public Comment addTaskComment(TaskComment taskComment,int userId);
    public List<TaskComment> getTaskComments(int taskId);
    public boolean deleteTaskComment(int userId,int taskId,int commentId);
    public List<Comment> getComments(int taskId);
    public Comment updateTaskComment(TaskComment taskComment,int userId,int commentId);
    public long getTaskCommentCount(int taskId);
}
