package com.alpha.classpie.type.test;


public enum TestElementType implements BaseEnumInf<Integer>{
    JUDGEMENT(0),SINGLE_CHOICE(1),MULTIPLE_CHOICE(2)
    ,INDETERMINATE(3),FILL_BLANK(4)
    ,NARRATIVE(5),FILE(6),PARAGRAPH(7);
    int i;
    TestElementType(int i){
        this.i=i;
    }

    public static TestElementType toTestElementType(Integer value){
        TestElementType[] values = values();
        for(TestElementType testElementType:values){
            if(value.equals(testElementType.getValue())){
                return testElementType;
            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        return this.i;
    }

    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        return toTestElementType(value);
    }
}
