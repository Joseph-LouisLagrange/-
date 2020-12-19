package com.alpha.classpie.type;


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
