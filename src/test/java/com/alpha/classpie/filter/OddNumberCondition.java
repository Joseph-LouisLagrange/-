package com.alpha.classpie.filter;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/29
 */
public class OddNumberCondition implements Condition {

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
        //基数
        return value%2!=0;
    }
}
