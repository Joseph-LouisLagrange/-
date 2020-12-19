package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;
import com.fasterxml.jackson.annotation.JsonTypeId;

import java.util.List;


public class FillBlank extends Problem{

    List<String> referenceAnswer;

    @JsonTypeId
    public static final TestElementType TYPE=TestElementType.FILL_BLANK;

    @Override
    public TestElementType getType() {
        return TYPE;
    }
}
