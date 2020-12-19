package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;
import com.fasterxml.jackson.annotation.JsonTypeId;


public class FileTest extends Problem{
    String referenceAnswer;

    @JsonTypeId
    public static final TestElementType TYPE=TestElementType.FILE;
    @Override
    public TestElementType getType() {
        return TYPE;
    }

    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }
}
