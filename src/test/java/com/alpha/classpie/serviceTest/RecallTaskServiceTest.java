package com.alpha.classpie.serviceTest;

import com.alpha.classpie.service.inf.RecallTaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
public class RecallTaskServiceTest {
    @Autowired
    RecallTaskService recallTaskService;
    public void  test(){

    }

    @ParameterizedTest
    @CsvSource({"102,22,10","102,22,15"})
    public void returnTaskTest(int actionUserId,int submittedTaskId,int userId) throws IOException {
        boolean b = recallTaskService.returnTask(actionUserId, submittedTaskId, userId);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @ValueSource(ints = {4})
    public void getAllReturnTask(int id){
        recallTaskService.getAllReturnTask(id).forEach(System.out::println);
    }
}
