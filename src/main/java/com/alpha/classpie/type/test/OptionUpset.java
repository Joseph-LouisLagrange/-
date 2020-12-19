package com.alpha.classpie.type.test;


public enum OptionUpset  implements BaseEnumInf<Integer>  {
    NO_UPSET(0),DO_UPSET(1);
    int i;
    OptionUpset(int i){
        this.i=i;
    }
    @Override
    public Integer getValue() {
        return this.i;
    }
    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        OptionUpset[] values = values();
        for(OptionUpset a:values){
            if(value.equals(a.getValue())){
                return a;
            }
        }
        return null;
    }
}
