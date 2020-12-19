package com.alpha.classpie.type.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public interface BaseEnumInf<T> {
    @JsonValue
    T getValue();


    @JsonCreator
    BaseEnumInf<T> fromValue(T value);
}
