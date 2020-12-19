package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;

import java.util.ArrayList;
import java.util.List;


public class SingleChoice extends Problem {
    List<Option> options=new ArrayList<>();
    Option correctOption;

    public static final TestElementType TYPE=TestElementType.SINGLE_CHOICE;

    @Override
    public TestElementType getType() {
        return TYPE;
    }
}
