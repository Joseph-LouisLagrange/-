package com.alpha.classpie.service.inf.comment;

import com.alpha.classpie.pojo.comment.BulletinComment;
import com.alpha.classpie.pojo.comment.Comment;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/24
 */
public interface BulletinCommentService extends CommentService {
    public Comment addBulletinComment(BulletinComment bulletinComment,int userId);

    public List<BulletinComment> getBulletinComments(int bulletinId);
}
