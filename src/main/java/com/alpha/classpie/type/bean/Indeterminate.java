package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;

import java.util.List;


public class Indeterminate extends Problem {
    List<Option> options;
    List<Option> correctOptions;

    public static final TestElementType TYPE=TestElementType.INDETERMINATE;

    @Override
    public TestElementType getType() {
        return TYPE;
    }
}
