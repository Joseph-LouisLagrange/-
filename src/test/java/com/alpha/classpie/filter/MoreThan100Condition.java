package com.alpha.classpie.filter;

import com.alpha.classpie.pojo.comment.CommentReply;

/**
 * @author 杨能
 * @create 2020/11/29
 */
public class MoreThan100Condition implements Condition {
    boolean active=false;
    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active=active;
    }

    @Override
    public boolean doTest(Integer value) {
        return value>100;
    }
}
