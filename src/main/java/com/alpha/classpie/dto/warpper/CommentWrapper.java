package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.pojo.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentWrapper extends Comment {
    String publisherName;
    List<CommentReplyWrapper> commentReplyList=new ArrayList<>();
}
