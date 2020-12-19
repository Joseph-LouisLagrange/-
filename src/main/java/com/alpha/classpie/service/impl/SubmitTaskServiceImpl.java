package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.ReturnTaskMapper;
import com.alpha.classpie.dao.SubmitTaskFileMapper;
import com.alpha.classpie.dao.SubmitTaskMapper;
import com.alpha.classpie.dao.TaskMapper;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.SubmitTaskExample;
import com.alpha.classpie.example.SubmitTaskFileExample;
import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.pojo.file.SubmitTaskFile;
import com.alpha.classpie.pojo.task.*;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.*;
import com.alpha.classpie.service.inf.notice.SendNoticeService;
import com.alpha.classpie.service.inf.notice.TaskNoticeService;
import com.alpha.classpie.util.ComputeUtil;
import com.alpha.classpie.util.Office2PDF;
import com.alpha.classpie.util.SendNoticeServiceHelper;
import com.alpha.classpie.util.file.EntryNode;
import com.alpha.classpie.util.file.FilePackHelper;
import org.jodconverter.core.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("defaultSubmitTaskService")
public class SubmitTaskServiceImpl implements SubmitTaskService {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    SubmitTaskMapper submitTaskMapper;
    @Autowired
    SubmitTaskFileMapper submitTaskFileMapper;
    @Resource(name = "defaultFilePlanService")
    FilePlanService filePlanService;
    @Resource(name = "defaultUserService")
    UserService userService;

    @Autowired
    FilePackHelper filePackHelper;

    @Autowired
    FileExportService fileExportService;

    @Autowired
    TaskNoticeService taskNoticeService;

    @Autowired
    ReturnTaskMapper returnTaskMapper;

    private SubmitTask internalSubmitTask(MultipartFile[] multipartFiles, Boolean isOnTime, Long wordCount, int taskId, int userId) throws IOException {
        SubmitTask submitTask = new SubmitTask(userId, taskId, false, 0, isOnTime, 0f, wordCount, Timestamp.valueOf(LocalDateTime.now()));
        submitTaskMapper.insert(submitTask);
        FilePlan[] filePlans = filePlanService.addMultipartFiles(multipartFiles);
        for (FilePlan filePlan : filePlans) {
            submitTaskFileMapper.insert(new SubmitTaskFile(userId, taskId, filePlan.getId()));
        }
        return submitTask;
    }

    @Override
    public SubmitTask submitTask(MultipartFile[] multipartFiles, int taskId, int userId) throws IOException {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadlineLocalDateTime = task.getDeadline().toLocalDateTime();
        long size = 0;
        if (deadlineLocalDateTime.isAfter(now)) {
            //表示按时提交，无论是严格还是宽松都是合理的可以提交的
            return internalSubmitTask(multipartFiles, true, ComputeUtil.computeWordCount(multipartFiles), taskId, userId);
        } else if (!task.getStrict() && deadlineLocalDateTime.isBefore(now)) {
            //未按时交
            return internalSubmitTask(multipartFiles, false,  ComputeUtil.computeWordCount(multipartFiles), taskId, userId);
        } else {
            //不允许交
            throw new ExceptionDto("作业提交拒绝", "不允许迟交", "与老师沟通");
        }
    }

    @Override
    public SubmitTask getSubmitTask(int taskId, int userId) {
        return submitTaskMapper.selectByPrimaryKey(new SubmitTaskKey(userId, taskId));
    }

    @Override
    public boolean canUseFile(int taskId, int userId, int fileId) {
        SubmitTaskFileExample submitTaskFileExample = new SubmitTaskFileExample();
        submitTaskFileExample.createCriteria().andTaskIdEqualTo(taskId).andUserIdEqualTo(userId).andFileIdEqualTo(fileId);
        return submitTaskFileMapper.countByExample(submitTaskFileExample)>0;
    }

    @Override
    public List<SubmitTask> getSubmitTasks(int taskId, Integer[] userId) {
        if(userId.length==0) return Collections.emptyList();
        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId).andUserIdIn(Arrays.asList(userId));
        return submitTaskMapper.selectByExample(submitTaskExample);
    }

    @Override
    public List<SubmitTask> getSubmitTasks(int taskId) {
        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId);
        return submitTaskMapper.selectByExample(submitTaskExample);
    }

    @Override
    public List<Student> getSubmittedStudent(int taskId) {
        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId);
        List<SubmitTask> submitTasks = submitTaskMapper.selectByExample(submitTaskExample);
        List<Integer> userIds = submitTasks.stream().map(SubmitTaskKey::getUserId).collect(Collectors.toList());
        return userService.getStudentByIds(userIds);
    }

    @Override
    public boolean isSubmitted(int userId, int taskId) {
        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId).andUserIdEqualTo(userId);
        return submitTaskMapper.countByExample(submitTaskExample)>0;
    }

    @Override
    public boolean correctSubmittedTask(int teacherId, int submittedTaskId, int userId, float score) {
        SubmitTask submitTask = new SubmitTask();
        submitTask.setTaskId(submittedTaskId);
        submitTask.setUserId(userId);
        submitTask.setIsCorrect(true);
        submitTask.setScore(score);
        return submitTaskMapper.updateByPrimaryKeySelective(submitTask) > 0 && taskNoticeService.addTaskNotice(userId,submittedTaskId,TaskNotice.correctType,teacherId);
    }

    @Override
    public boolean deleteSubmittedTask(int submittedTaskId, int userId) throws IOException {
        SubmitTaskFileExample submitTaskFileExample = new SubmitTaskFileExample();
        submitTaskFileExample.createCriteria().andTaskIdEqualTo(submittedTaskId).andUserIdEqualTo(userId);
        List<SubmitTaskFile> submitTaskFiles = submitTaskFileMapper.selectByExample(submitTaskFileExample);
        if(!submitTaskFiles.isEmpty()){
            List<Integer> fileIds = submitTaskFiles.stream().map(SubmitTaskFile::getFileId).collect(Collectors.toList());
            filePlanService.deleteFilePlan(fileIds);
        }
        return submitTaskMapper.deleteByPrimaryKey(new SubmitTaskKey(userId, submittedTaskId)) > 0;
    }

    @Override
    public SubmitTask updateSubmitTask(int userId, int taskId, List<Integer> deleteFileIds, MultipartFile[] addNewMultipartFile) throws IOException {
        //先保存预删除的文件的总字数
        Long deleteSize = deleteFileIds.stream().map(filePlanService::getFilePlan).map(FilePlan::getSize).reduce(Long::sum).orElse(0L);
        //先删除文件
        filePlanService.deleteFilePlan(deleteFileIds);
        SubmitTask submitTask = submitTaskMapper.selectByPrimaryKey(new SubmitTaskKey(userId, taskId));
        //更新提交表
        submitTask.setDatetime(Timestamp.valueOf(LocalDateTime.now()));
        long wordCount=submitTask.getWordCount()-ComputeUtil.computeWordCount(deleteSize);
        //放入文件
        if(addNewMultipartFile!=null){
            wordCount+=ComputeUtil.computeWordCount(addNewMultipartFile);
            FilePlan[] filePlans = filePlanService.addMultipartFiles(addNewMultipartFile);
            Arrays.stream(filePlans)
                    .map(filePlan -> new SubmitTaskFile(userId,taskId,filePlan.getId()))
                    .forEach(submitTaskFileMapper::insert);//插入提交表
        }
        submitTask.setWordCount(wordCount);//重新计算字数
        submitTaskMapper.updateByPrimaryKey(submitTask);
        return submitTask;
    }

    @Override
    public void downAllSubmittedTask(int taskId, HttpServletResponse response) throws IOException {
        EntryNode entryNode = fileExportService.exportAllSubmittedTask(taskId);
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding("UTF-8");
        //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(entryNode.getName()+".zip", "UTF-8").replaceAll("\\+","%20"));
        // 告知浏览器文件的大小
        //response.addHeader("Content-Length", "" + );
        filePackHelper.pack(response.getOutputStream(),entryNode);
    }

    @Override
    public void downSubmittedTaskFile(int fileId,HttpServletResponse response) throws IOException {
        FilePlan filePlan = filePlanService.getFilePlan(fileId);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filePlan.getName(), "UTF-8").replaceAll("\\+","%20"));
        filePlanService.transferTo(response,filePlan);
    }

    @Override
    public void previewSubmittedTaskFile(int fileId, HttpServletResponse response) throws IOException, OfficeException {
        FilePlan filePlan = filePlanService.getFilePlan(fileId);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(filePlan.getName(), "UTF-8").replaceAll("\\+","%20"));
        response.setContentType("application/pdf");
        filePlanService.transferToPDF(response,filePlan);
        response.getOutputStream().close();
    }

    @Override
    public void downSomeSubmittedTask(int taskId,Integer[] userIds, HttpServletResponse response) throws IOException {
        EntryNode entryNode = fileExportService.exportSubmittedTask(taskId,userIds);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(entryNode.getName()+".zip", "UTF-8").replaceAll("\\+","%20"));
        filePackHelper.pack(response.getOutputStream(),entryNode);
    }

    @Override
    public boolean batchCorrectSubmittedTask(Integer[] userIds, int submittedTaskId, int teacherId, float score) {
        SubmitTask submitTask = new SubmitTask();
        submitTask.setIsCorrect(true);
        submitTask.setScore(score);
        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andUserIdIn(Arrays.asList(userIds)).andTaskIdEqualTo(submittedTaskId);
        submitTaskMapper.updateByExampleSelective(submitTask,submitTaskExample);
        //发出通知
        taskNoticeService.addTaskNotice(Arrays.asList(userIds),submittedTaskId,TaskNotice.correctType,teacherId);
        return true;
    }
}
