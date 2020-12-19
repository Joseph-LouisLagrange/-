package com.alpha.classpie.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {


    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T fromJson(String json,Class<T> tClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,tClass);
    }

    public static <C,E> C fromJson(String json,Class<C> cClass,Class<E> elementClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType collectionType = getCollectionType(cClass, elementClass);
        return objectMapper.readValue(json,collectionType);
    }
}
