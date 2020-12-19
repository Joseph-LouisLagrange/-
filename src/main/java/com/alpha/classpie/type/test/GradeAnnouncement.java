package com.alpha.classpie.type.test;


public enum GradeAnnouncement implements BaseEnumInf<Integer> {
    NO_ANNOUNCE(0),DO_ANNOUNCE(1);
    int i;
    GradeAnnouncement(int i){
        this.i=i;
    }
    @Override
    public Integer getValue() {
        return this.i;
    }
    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        GradeAnnouncement[] values = values();
        for(GradeAnnouncement a:values){
            if(value.equals(a.getValue())){
                return a;
            }
        }
        return null;
    }
}
