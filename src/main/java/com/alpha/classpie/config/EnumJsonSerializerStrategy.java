package com.alpha.classpie.config;

import com.alpha.classpie.type.test.BaseEnumInf;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;



public class EnumJsonSerializerStrategy extends StdSerializer<BaseEnumInf> {

    protected EnumJsonSerializerStrategy(Class<BaseEnumInf> t) {
        super(t);
    }

    @Override
    public void serialize(BaseEnumInf value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(value.getValue());
    }
}
