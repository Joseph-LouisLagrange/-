package com.alpha.classpie.serviceTest;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dao.TaskMapper;
import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.task.TaskCheckDuplicate;
import com.alpha.classpie.service.inf.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    DataWrapper dataWrapper;

    byte[] bytes=RandomUtil.randomBytes(1024*1024*3);

    @ParameterizedTest
    @ValueSource(ints = {4})
    public void powerEditTaskTest(int taskId) throws IOException {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        task.setFullMarks(1000);
       MultipartFile[] multipartFiles=null;
//        multipartFiles[0]=new MockMultipartFile("file", RandomUtil.randomString(6)+".txt","text/plain",bytes);
//        multipartFiles[1]=new MockMultipartFile("file", RandomUtil.randomString(6)+".txt","text/plain",bytes);
        List<Integer> convert = Convert.convert(new TypeReference<List<Integer>>() {
        }, ArrayUtil.range(27, 39));
        Task powerEditTask = taskService.powerEditTask(task, taskId, multipartFiles,convert);
        System.out.print(powerEditTask);
    }

    @ParameterizedTest
    @ValueSource(ints = {4})
    public void simpleEditTaskTest(int taskId){
        Task task = taskMapper.selectByPrimaryKey(taskId);
        task.setDeadline(Timestamp.valueOf(LocalDateTime.now()));
        taskService.simpleEditTask(task,task.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {5})
    public void getUnpaidStudentsTest(int taskId){
        taskService.getUnpaidStudents(taskId).forEach(System.out::println);
    }


    @Test
    public void  editTaskTest(){
        Task task = new Task(4,10,"作业3", Timestamp.valueOf(LocalDateTime.now()),100,true,1,Timestamp.valueOf(LocalDateTime.now()),true,"作业的简历");
        task.setTaskCheckDuplicate(new TaskCheckDuplicate(4,20,true,30));
        Task editTask = taskService.simpleEditTask(task, 4);
        System.out.println(editTask);
    }




    @Test
    public void releaseTaskTest() throws IOException {
        Task task1 = new Task(0,10,"算法作业cc", Timestamp.valueOf(LocalDateTime.now()),100,true,1,Timestamp.valueOf(LocalDateTime.of(2020,12,9,5,1)),false,"快点交");
        TaskCheckDuplicate taskCheckDuplicate = new TaskCheckDuplicate();
        taskCheckDuplicate.setIsReturn(true);
        taskCheckDuplicate.setReturnValue(50);
        taskCheckDuplicate.setWarningValue(20);
        task1.setTaskCheckDuplicate(taskCheckDuplicate);
        Task task2 = new Task(0,10,"算法作业ddd", Timestamp.valueOf(LocalDateTime.now()),100,true,1,Timestamp.valueOf(LocalDateTime.of(2020,12,9,5,1)),false,"快点交");
        MultipartFile multipartFile1=new MockMultipartFile("file","新建文件1.txt","text/plain",new byte[]{1,2,3});
        MultipartFile multipartFile2=new MockMultipartFile("file","新建文件2.txt","text/plain",new byte[]{1,2,3});
        taskService.releaseDoCheckDuplicateTask(task1, new MultipartFile[]{multipartFile2,multipartFile1}, 1);
        //taskService.releaseNoCheckDuplicateTask(task2, new MultipartFile[]{multipartFile}, 102);
    }

    @ParameterizedTest
    @ValueSource(ints = {7})
    public void deleteTaskTest(int id) throws IOException {
        Assertions.assertTrue(taskService.deleteTask(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getReleasedTasksTest(int courseId){
        taskService.getReleasedTasks(courseId).stream().map(dataWrapper::doTeacherViewTaskWrap).forEach(System.out::println);
    }

    @Test
    public void DoCheckDuplicateTaskTest() throws IOException {
        Task task = new Task(0,10,"作业1", Timestamp.valueOf(LocalDateTime.now()),100,true,1,Timestamp.valueOf(LocalDateTime.now()),true,"作业的简历");
        MultipartFile multipartFile1=new MockMultipartFile("file","ddd.txt","text/plain",new byte[]{1,2,3});
        MultipartFile multipartFile2=new MockMultipartFile("file","dd.txt","text/plain",new byte[]{1,2,3});
        task.setTaskCheckDuplicate(new TaskCheckDuplicate(null,20,true,30));
        Task task1 = taskService.releaseDoCheckDuplicateTask(task, new MultipartFile[]{multipartFile1,multipartFile2}, 1);
        System.out.println(task1);
    }
}
