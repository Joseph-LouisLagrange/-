package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.pojo.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 杨能
 * @create 2020/11/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentReplyWrapper extends CommentReply{
    String publisherName;
}
