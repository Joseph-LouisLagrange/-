package com.alpha.classpie.config;

import com.alpha.classpie.type.bean.*;
import com.alpha.classpie.type.test.TestElementType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import java.io.IOException;
import java.util.*;


public class TestWorkElementTypeIdResolver extends TypeIdResolverBase {

    private static Map<TestElementType,Class<? extends TestWorkElement>> map=new Hashtable<>();

    static {
        map.put(TestElementType.JUDGEMENT, Judgment.class);
        map.put(TestElementType.MULTIPLE_CHOICE, MultipleChoice.class);
        map.put(TestElementType.INDETERMINATE, Indeterminate.class);
        map.put(TestElementType.FILL_BLANK, FillBlank.class);
        map.put(TestElementType.NARRATIVE,Narrative.class);
        map.put(TestElementType.FILE,FileTest.class);
        map.put(TestElementType.PARAGRAPH,ParagraphDescription.class);
    }

    @Override
    public String idFromValue(Object value) {
        TestWorkElement testWorkElement= (TestWorkElement) value;
        return String.valueOf(testWorkElement.getType().getValue());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return null;
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        return context.constructType(map.get(TestElementType.toTestElementType(Integer.parseInt(id))));
    }
}
