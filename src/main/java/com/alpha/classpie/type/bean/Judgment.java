package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;


public class Judgment extends Problem {
    boolean referenceAnswer;
    public static final TestElementType TYPE=TestElementType.JUDGEMENT;

    @Override
    public TestElementType getType() {
        return TYPE;
    }
}
