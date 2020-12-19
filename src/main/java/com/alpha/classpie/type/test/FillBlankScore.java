package com.alpha.classpie.type.test;


public enum FillBlankScore implements BaseEnumInf<Integer>  {
    MANUAL_CORRECT(0),AUTO_CORRECT_BY_MACHINE(1),AUTO_ZERO(3);
    int i;
    FillBlankScore(int i){
        this.i=i;
    }
    @Override
    public Integer getValue() {
        return this.i;
    }
    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        FillBlankScore[] values = values();
        for(FillBlankScore a:values){
            if(value.equals(a.getValue())){
                return a;
            }
        }
        return null;
    }
}
