package com.alpha.classpie.type;


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
