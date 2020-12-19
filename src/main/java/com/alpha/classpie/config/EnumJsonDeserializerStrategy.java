package com.alpha.classpie.config;

import com.alpha.classpie.type.test.BaseEnumInf;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;


public class EnumJsonDeserializerStrategy<T extends BaseEnumInf<?>> extends StdDeserializer<T> {

    protected EnumJsonDeserializerStrategy(Class<T> vc) {
        super(vc);
    }

    protected EnumJsonDeserializerStrategy(JavaType valueType) {
        super(valueType);
    }

    protected EnumJsonDeserializerStrategy(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        return null;
    }
}
