package com.alpha.classpie.type;

/**
 * @author 杨能
 * @create 2020/11/9
 */
public enum Sex {
    MAN("男"),WOMAN("女");
    String s;
    Sex(String s){
        this.s=s;
    }
    public String getSexValue(){
        return this.s;
    }
}
