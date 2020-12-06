package com.alpha.classpie.filter;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/29
 */
public interface Condition {
    public boolean isActive();
    public void setActive(boolean active);
    public boolean doTest(Integer value);
}
