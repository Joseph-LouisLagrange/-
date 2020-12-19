package com.alpha.classpie.serviceTest;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.service.inf.SubmitTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class SubmitTaskServiceTest {

    MultipartFile multipartFile1=new MockMultipartFile("file",RandomUtil.randomString(6)+".txt","text/plain", RandomUtil.randomBytes(1024));
    MultipartFile multipartFile2=new MockMultipartFile("file",RandomUtil.randomString(6)+".txt","text/plain",RandomUtil.randomBytes(1024));
    @Autowired
    SubmitTaskService submitTaskService;

    @Autowired
    DataWrapper dataWrapper;

    ObjectMapper objectMapper=new ObjectMapper();

    @ParameterizedTest
    @CsvSource("1,5,10,66")
    public void correctSubmittedTaskTest(int teacherId,int submittedTaskId,int userId,float score){
        boolean b = submitTaskService.correctSubmittedTask(teacherId, submittedTaskId, userId, score);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @CsvSource("15,4")
    public void updateSubmitTaskTest(int userId,int taskId) throws IOException {
        submitTaskService.updateSubmitTask(userId,taskId,Arrays.asList(44,45),null);
    }

    @ParameterizedTest
    @CsvSource("15,4")
    public void deleteSubmittedTaskTest(int userId,int taskId) throws IOException {
        boolean b = submitTaskService.deleteSubmittedTask(taskId, userId);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @ValueSource(ints = {4})
    public void getSubmitTasksTest(int taskId){
        List<SubmitTask> submitTasks = submitTaskService.getSubmitTasks(taskId);
        submitTasks.forEach(submitTask -> {
            try {
                System.out.println(objectMapper.writeValueAsString(dataWrapper.doSubmitTaskTeacherViewWrap(submitTask)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @ParameterizedTest
    @CsvSource({"10,4"})
    public void getSubmitTaskTest(int userId,int taskId) throws JsonProcessingException {
        SubmitTask submitTask = submitTaskService.getSubmitTask(taskId,userId);
        System.out.println(objectMapper.writeValueAsString(dataWrapper.doSubmitTaskTeacherViewWrap(submitTask)));
    }

    @ParameterizedTest
    @CsvSource(value = {"22,15","22,10"})
    public void submitTaskTest(int taskId,int userId) throws IOException {
        SubmitTask submitTask = submitTaskService.submitTask(new MultipartFile[]{multipartFile1, multipartFile2}, taskId, userId);
        System.out.println(dataWrapper.doSubmitTaskTeacherViewWrap(submitTask));
    }

}
