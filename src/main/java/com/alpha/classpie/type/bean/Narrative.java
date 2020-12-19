package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;


public class Narrative extends Problem {
    String referenceAnswer;

    public static final TestElementType TYPE=TestElementType.NARRATIVE;

    @Override
    public TestElementType getType() {
        return TYPE;
    }
}
