package com.alpha.classpie.type.bean;

import com.alpha.classpie.config.TestWorkElementTypeIdResolver;
import com.alpha.classpie.type.test.TestElementType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;




@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM,include = JsonTypeInfo.As.EXISTING_PROPERTY,property = "type")
@JsonTypeIdResolver(value = TestWorkElementTypeIdResolver.class)
public abstract class TestWorkElement {
    //序号
    int sequenceNumber;
    public abstract TestElementType getType();
}
