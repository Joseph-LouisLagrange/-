package com.alpha.classpie;



import com.alpha.classpie.type.bean.FileTest;
import com.alpha.classpie.type.bean.FillBlank;
import com.alpha.classpie.type.bean.TestWorkElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;



@SpringBootTest
public class TempTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {
        FileTest fileTest=new FileTest();
        FillBlank fillBlank=new FillBlank();
        String json = objectMapper.writeValueAsString(Arrays.asList(fileTest,fillBlank));
        System.out.println(json);
        System.out.println(objectMapper.readValue(json, new TypeReference<List<TestWorkElement>>() {
        }).get(0).getClass());
        System.out.println(objectMapper.readValue(json, new TypeReference<List<TestWorkElement>>() {
        }).get(1).getClass());
    }
}
