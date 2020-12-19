package com.alpha.classpie.filter;

import java.util.List;


public interface Condition {
    public boolean isActive();
    public void setActive(boolean active);
    public boolean doTest(Integer value);
}
