package com.alpha.classpie.type.test;


public enum MultipleChoiceScore  implements BaseEnumInf<Integer>  {
    STRICT(0),USE_OPTION_COUNT_FOR_LESS(1),HALF_FOR_LESS(2);
    int i;
    MultipleChoiceScore(int i){
        this.i=i;
    }
    @Override
    public Integer getValue() {
        return this.i;
    }
    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        MultipleChoiceScore[] values = values();
        for(MultipleChoiceScore a:values){
            if(value.equals(a.getValue())){
                return a;
            }
        }
        return null;
    }

}
