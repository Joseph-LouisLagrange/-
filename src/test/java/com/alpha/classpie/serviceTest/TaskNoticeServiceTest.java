package com.alpha.classpie.serviceTest;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.task.TaskNotice;
import com.alpha.classpie.service.inf.notice.TaskNoticeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/21
 */
@SpringBootTest
public class TaskNoticeServiceTest {

    @Autowired
    TaskNoticeService taskNoticeService;

    ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    DataWrapper dataWrapper;

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void markAllBeReadTaskNoticeTest(int userId){
        boolean b = taskNoticeService.markAllBeReadNotice(userId);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getAllTaskNoticeTest(int userId){
        List<TaskNotice> allTaskNotice = taskNoticeService.getAllTaskNotice(userId);
        allTaskNotice.stream().map(taskNotice -> {
            try {
                return objectMapper.writeValueAsString(taskNotice);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }

    @ParameterizedTest
    @CsvSource("10,1,批阅,1")
    public void addTaskNoticeTest(int userId,int taskId,String type,int actionUserId){
        boolean b = taskNoticeService.addTaskNotice(userId, taskId, type, actionUserId);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @CsvSource("2,10")
    public void markReadTaskNoticeTest(int noticeId,int userId){
        boolean b = taskNoticeService.markReadTaskNotice(noticeId, userId);
        Assertions.assertTrue(b);
    }
}
