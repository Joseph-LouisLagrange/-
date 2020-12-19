package com.alpha.classpie.service.impl.notice;


import com.alpha.classpie.dao.TaskNoticeMapper;

import com.alpha.classpie.example.TaskNoticeExample;
import com.alpha.classpie.pojo.Notice;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.task.TaskNotice;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.*;

import com.alpha.classpie.service.inf.notice.AbstractNoticeService;
import com.alpha.classpie.service.inf.notice.SendNoticeService;

import com.alpha.classpie.service.inf.notice.TaskNoticeService;
import com.alpha.classpie.util.SendNoticeServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service("defaultTaskNoticeService")
@Transactional
public class TaskNoticeServiceImpl extends AbstractNoticeService implements TaskNoticeService {

    @Autowired
    TaskNoticeMapper taskNoticeMapper;

/**************************************通知的发送器************************************/
    @Resource(name = "releaseTask")
    private SendNoticeService releaseTaskSendNoticeService;

    @Resource(name = "correctTask")
    private SendNoticeService correctTaskSendNoticeService;

    @Resource(name = "recallTask")
    private SendNoticeService recallTaskSendNoticeService;

    @Resource(name = "expediteTask")
    private SendNoticeService expediteTaskSendNoticeService;

    /**************************************************************************/
    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @Resource(name = "defaultUserService")
    UserService userService;

    @Resource(name = "defaultTaskService")
    TaskService taskService;

    Map<String, SendNoticeService> noticeServiceStrategy=null;

    @Autowired
    SendNoticeServiceHelper sendNoticeServiceHelper;

    protected String getCompliedContent(String pattern,Object...ele){
       return MessageFormat.format(pattern,ele);
    }

    public synchronized SendNoticeService getSendNoticeService(String type){
        if(noticeServiceStrategy==null){
            noticeServiceStrategy=new Hashtable<String, SendNoticeService>(4);
            //初始化4种策略
            noticeServiceStrategy.put(TaskNotice.correctType, correctTaskSendNoticeService);
            noticeServiceStrategy.put(TaskNotice.expediteType, expediteTaskSendNoticeService);
            noticeServiceStrategy.put(TaskNotice.recallType, recallTaskSendNoticeService);
            noticeServiceStrategy.put(TaskNotice.releaseType, releaseTaskSendNoticeService);
        }
        return noticeServiceStrategy.get(type);
    }

    @Override
    public List<TaskNotice> getAllNoReadTaskNotice(int userId) {
        return taskNoticeMapper.getNoReadTaskNoticeByUserId(userId);
    }

    @Override
    public List<TaskNotice> getAllTaskNotice(int userId) {
        return taskNoticeMapper.getAllTaskNoticeByUserId(userId);
    }

    @Override
    public boolean addTaskNotice(int userId,int taskId,String type,int actionUserId) {
        User toUser = userService.getUserById(userId);
        User teacher=userService.getUserById(actionUserId);
        Task task = taskService.getTaskById(taskId);
        return addTaskNotice(toUser,task,type,teacher);
    }

    protected boolean addTaskNotice(User toUser,Task task,String type,User actionUser){
        SendNoticeService sendNoticeService = getSendNoticeService(type);
        Assert.notNull(sendNoticeService,"task通知类型无效");
        Course course = courseService.getCourseById(task.getCourseId());
        sendNoticeServiceHelper.sendTaskNotice(sendNoticeService,toUser,course,actionUser,task);
        Notice notice=new Notice(0,toUser.getId(), sendNoticeService.getSubject(),false,
                sendNoticeService.getCompliedContent(course.getName(),actionUser.getName(),task.getName()));
        super.addNotice(notice);
        return taskNoticeMapper.insert(new TaskNotice(notice.getId(),task.getId(),type))>0;
    }

    @Override
    public boolean addTaskNotice(List<Integer> userId, int taskId, String type, int actionUserId) {
        List<User> users = userService.getUsersByIds(userId);
        User teacher=userService.getUserById(actionUserId);
        Task task = taskService.getTaskById(taskId);
        boolean result=true;
        for(User user:users){
            result=result&&this.addTaskNotice(user,task,type,teacher);
        }
        return result;
    }


    @Override
    public boolean markReadTaskNotice(int noticeId, int userId) {
        checkBelonging(noticeId, userId);
        return super.markReadNotice(noticeId);
    }

    @Override
    public boolean deleteTaskNotice(int taskId) {
        TaskNoticeExample taskNoticeExample = new TaskNoticeExample();
        taskNoticeExample.createCriteria().andTaskIdEqualTo(taskId);
        List<Integer> noticeIds = taskNoticeMapper.selectByExample(taskNoticeExample).stream().map(TaskNotice::getNoticeId).collect(Collectors.toList());
        return super.deleteNotice(noticeIds);
    }
}
