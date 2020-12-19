package com.alpha.classpie.type.bean;

import com.alpha.classpie.type.test.TestElementType;


public class ParagraphDescription extends TestWorkElement {
    String description;

    public static final TestElementType TYPE=TestElementType.PARAGRAPH;

    @Override
    public TestElementType getType() {
        return TYPE;
    }
}
