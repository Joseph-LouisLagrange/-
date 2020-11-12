package com.alpha.classpie.type;

/**
 * @author 杨能
 * @create 2020/11/9
 */
public enum ChineseBool {
    TRUE("是"),FALSE("否");
    String s;
    ChineseBool(String s){
        this.s=s;
    }
    public String getValue(){
        return this.s;
    }
}
