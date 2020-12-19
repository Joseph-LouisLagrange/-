package com.alpha.classpie.type.test;


public enum ProblemUpset implements BaseEnumInf<Integer>  {
    NO_UPSET(0),ALL_PROBLEM_UPSET(1),SAME_BLOCK_UPSET(2);
    int i;
    ProblemUpset(int i){
        this.i=i;
    }
    @Override
    public Integer getValue() {
        return this.i;
    }
    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        ProblemUpset[] values = values();
        for(ProblemUpset a:values){
            if(value.equals(a.getValue())){
                return a;
            }
        }
        return null;
    }
}
