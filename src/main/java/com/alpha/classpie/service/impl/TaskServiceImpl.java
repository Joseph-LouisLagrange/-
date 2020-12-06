package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.*;
import com.alpha.classpie.example.ReleaseTaskFileExample;
import com.alpha.classpie.example.SubmitTaskExample;
import com.alpha.classpie.example.TaskCommentExample;
import com.alpha.classpie.example.TaskExample;
import com.alpha.classpie.pojo.comment.TaskComment;
import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.pojo.file.ReleaseTaskFile;
import com.alpha.classpie.pojo.file.ReleaseTaskFileKey;
import com.alpha.classpie.pojo.task.*;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.*;
import com.alpha.classpie.service.inf.notice.SendNoticeService;
import com.alpha.classpie.service.inf.notice.TaskNoticeService;
import com.alpha.classpie.util.SendNoticeServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/15
 */
@Service("defaultTaskService")
@Transactional
public class TaskServiceImpl implements TaskService {


    @Autowired
    TaskMapper taskMapper;



    @Autowired
    ReleaseTaskFileMapper releaseTaskFileMapper;


    @Autowired
    TaskCheckDuplicateMapper taskCheckDuplicateMapper;

    @Autowired
    SubmitTaskMapper submitTaskMapper;

    @Autowired
    SubmitTaskFileMapper submitTaskFileMapper;

    @Autowired
    ExpediteTaskMapper expediteTaskMapper;


    @Resource(name = "defaultFilePlanService")
    FilePlanService filePlanService;

    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @Autowired
    TaskNoticeService taskNoticeService;

    @Autowired
    UserService userService;


    @Override
    public Task releaseDoCheckDuplicateTask(Task task, MultipartFile[] multipartFiles, int userId) throws IOException {
        task.setIsCheckDuplicate(true);
        task=releaseTask(task, multipartFiles, userId);
        task.getTaskCheckDuplicate().setTaskId(task.getId());
        taskCheckDuplicateMapper.insert(task.getTaskCheckDuplicate());
        return task;
    }

    protected Task releaseTask(Task task,MultipartFile[] multipartFiles, int userId) throws IOException {
        task.setPromulgator(userId);
        task.setReleaseTime(Timestamp.valueOf(LocalDateTime.now()));
        taskMapper.insert(task);
        Integer courseId = task.getCourseId();
        if(multipartFiles.length==0) return task;
        FilePlan[] filePlans = filePlanService.addMultipartFiles(multipartFiles);
        for(FilePlan filePlan : filePlans){
            releaseTaskFileMapper.insert(new ReleaseTaskFile(task.getId(),filePlan.getId(),task.getPromulgator()));
        }
        //发出通知
        taskNoticeService.addTaskNotice(
                courseService.getStudents(courseId).stream().map(User::getId).collect(Collectors.toList()),
                task.getId(),TaskNotice.releaseType,userId);
        return task;
    }

    @Override
    public Task releaseNoCheckDuplicateTask(Task task, MultipartFile[] multipartFiles, int userId) throws IOException {
        task.setIsCheckDuplicate(false);
        return releaseTask(task, multipartFiles, userId);
    }

    @Override
    public List<Task> getReleasedTasks(int courseId) {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andCourseIdEqualTo(courseId);
        List<Task> tasks = taskMapper.selectByExampleWithBLOBs(taskExample);
        for(Task task:tasks){
            if(task.getIsCheckDuplicate()){
                //加载查重的信息
                task.setTaskCheckDuplicate(taskCheckDuplicateMapper.selectByPrimaryKey(task.getId()));
            }
        }
        return tasks;
    }

    /**
     * 仅仅支持编辑 task 本身，无法及时对文件进行增删的控制
     * @param task 任务
     * @param taskId 目标id
     * @return 修改后的 task
     */
    @Override
    public Task simpleEditTask(Task task, int taskId) {
        Task oldTask = taskMapper.selectByPrimaryKey(taskId);
        if(!oldTask.getIsCheckDuplicate() && task.getIsCheckDuplicate()) {
            //表示old的是不用查重的，现在的插入的是要查重的饿
            task.getTaskCheckDuplicate().setTaskId(taskId);
            taskCheckDuplicateMapper.insert(task.getTaskCheckDuplicate());
        }else if(oldTask.getIsCheckDuplicate()){
            if(task.getIsCheckDuplicate()){
                task.getTaskCheckDuplicate().setTaskId(taskId);
                //更新的查重表
                taskCheckDuplicateMapper.updateByPrimaryKey(task.getTaskCheckDuplicate());
            }else{
                //删除查重记录
                taskCheckDuplicateMapper.deleteByPrimaryKey(taskId);
            }
        }
        taskMapper.updateByPrimaryKeyWithBLOBs(task);
        return task;
    }

    @Override
    public Task powerEditTask(Task task, int taskId, MultipartFile[] addNewMultipartFiles, List<Integer> deleteReleasedTaskFileId) throws IOException {
        Task result = simpleEditTask(task, taskId);
        //删除文件
        deleteReleasedTaskFileId.forEach(filePlanService::deleteFilePlan);
        //新增文件
        if(addNewMultipartFiles!=null) {
            FilePlan[] filePlans = filePlanService.addMultipartFiles(addNewMultipartFiles);
            for (FilePlan filePlan : filePlans) {
                releaseTaskFileMapper.insert(new ReleaseTaskFile(task.getId(), filePlan.getId(), task.getPromulgator()));
            }
        }
        return result;
    }

    @Override
    public boolean deleteTask(int taskId) throws IOException {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        taskNoticeService.deleteTaskNotice(taskId);
        //删除文件
        ReleaseTaskFileExample releaseTaskFileExample = new ReleaseTaskFileExample();
        releaseTaskFileExample.createCriteria().andTaskIdEqualTo(taskId);
        List<ReleaseTaskFile> releaseTaskFiles = releaseTaskFileMapper.selectByExample(releaseTaskFileExample);
        List<Integer> fileIds = releaseTaskFiles.stream().map(ReleaseTaskFileKey::getFileId).collect(Collectors.toList());
        //批量删除
        return filePlanService.deleteFilePlan(fileIds) && taskMapper.deleteByPrimaryKey(taskId) > 0;
    }

    //打包下载所有的作业文件到目标的 emailAddress
    @Override
    public void downloadAllTask(int courseId, String emailAddress) {

    }

    @Override
    public List<User> getUnpaidStudents(int taskId) {
        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId);
        List<SubmitTask> submitTasks = submitTaskMapper.selectByExample(submitTaskExample);
        Task task = taskMapper.selectByPrimaryKey(taskId);
        Integer courseId = task.getCourseId();
        if(submitTasks.isEmpty()){
            //表示全部的学生都没交
            return courseService.getStudents(courseId);
        }
        //已经提交的userId
        List<Integer> submitUserIds = submitTasks.stream().map(SubmitTaskKey::getUserId).collect(Collectors.toList());
        return courseService.getStudents(courseId,submitUserIds);
    }

    @Override
    public Task getTaskById(int id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean canUse(int userId, int taskId) {
        Task taskById = getTaskById(taskId);
        if(taskById==null) return false;
        Integer courseId = taskById.getCourseId();
        return courseService.hasInCourse(userId,courseId);
    }

    @Override
    public void downTaskFile(int fileId, HttpServletResponse response) throws IOException {
        FilePlan filePlan = filePlanService.getFilePlan(fileId);
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding("UTF-8");
        //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filePlan.getName(), "UTF-8").replace("+", "%20"));
        // 告知浏览器文件的大小
        response.addHeader("Content-Length", "" + filePlan.getSize());
        filePlanService.transferTo(response.getOutputStream(),filePlan);
        response.flushBuffer();
    }

    @Override
    public List<ReleaseTaskFile> getReleaseTaskFiles(int taskId) {
        ReleaseTaskFileExample releaseTaskFileExample = new ReleaseTaskFileExample();
        releaseTaskFileExample.createCriteria().andTaskIdEqualTo(taskId);
        return releaseTaskFileMapper.selectByExample(releaseTaskFileExample);
    }

    @Override
    public boolean expediteTask(int teacherId,int taskId, int toUserId) {
        ExpediteTaskKey expediteTaskKey = new ExpediteTaskKey(taskId, toUserId);
        ExpediteTask expediteTask = expediteTaskMapper.selectByPrimaryKey(expediteTaskKey);
        if(expediteTask==null){
            expediteTaskMapper.insert(new ExpediteTask(taskId,toUserId,1));
        }else {
            expediteTaskMapper.selfIncreaseCount(new ExpediteTaskKey(taskId, toUserId));
        }
        return taskNoticeService.addTaskNotice(toUserId,taskId,TaskNotice.expediteType,teacherId);
    }

    @Override
    public boolean batchExpediteTask(int teacherId, int taskId, int[] toUserId) {
        for(int id:toUserId){
            expediteTask(teacherId,taskId,id);
        }
        return true;
    }

    @Override
    public long getExpediteCount(int taskId, int userId) {
        ExpediteTask expediteTask = expediteTaskMapper.selectByPrimaryKey(new ExpediteTaskKey(taskId, userId));
        if(expediteTask==null) return 0;
        return expediteTask.getCount();
    }


    @Override
    public List<Task> list(TaskExample example) {
        return taskMapper.selectByExample(example);
    }
}
