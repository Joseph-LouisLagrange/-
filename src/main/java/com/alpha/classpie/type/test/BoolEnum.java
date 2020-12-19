package com.alpha.classpie.type.test;


public enum BoolEnum implements BaseEnumInf<Integer> {
    FALSE(0),TRUE(1);
    int i;
    BoolEnum(int i){
        this.i=i;
    }

    public boolean getBool(){
        return this.i==1;
    }

    @Override
    public Integer getValue() {
        return this.i;
    }

    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        return value==0?FALSE:TRUE;
    }

}
