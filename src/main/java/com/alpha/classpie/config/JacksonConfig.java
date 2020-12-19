package com.alpha.classpie.config;

import com.alpha.classpie.type.test.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.TimeZone;


@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(@Autowired Jackson2ObjectMapperBuilder builder)
    {
        ObjectMapper objectMapper = builder
                .simpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .timeZone("GMT+8")
                .createXmlMapper(false).build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        //同意反序列化时忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE,true);

        registerSubtypes(objectMapper);

        //registerModule(objectMapper);
        return objectMapper;
    }

    public void registerSubtypes(ObjectMapper objectMapper){

    }

    public void registerModule(ObjectMapper objectMapper){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(TestElementType.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(AnswerAnnouncement.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(BoolEnum.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(FillBlankScore.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(GradeAnnouncement.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(MultipleChoiceScore.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(OptionUpset.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        simpleModule.addSerializer(ProblemUpset.class,new EnumJsonSerializerStrategy(BaseEnumInf.class));
        //注册枚举的反序列

        objectMapper.registerModule(simpleModule);
    }
}
