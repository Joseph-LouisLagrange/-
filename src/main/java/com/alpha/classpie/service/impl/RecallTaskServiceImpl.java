package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.ReturnTaskMapper;
import com.alpha.classpie.example.ReturnTaskExample;
import com.alpha.classpie.pojo.task.ReturnTask;
import com.alpha.classpie.pojo.task.ReturnTaskKey;
import com.alpha.classpie.pojo.task.TaskNotice;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.*;
import com.alpha.classpie.service.inf.notice.TaskNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/20
 */
@Service("defaultRecallTaskService")
public class RecallTaskServiceImpl implements RecallTaskService {

    @Resource(name = "defaultSubmitTaskService")
    SubmitTaskService submitTaskService;
    @Autowired
    ReturnTaskMapper returnTaskMapper;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskNoticeService taskNoticeService;

    @Override
    public boolean returnTask(int actionUserId, int submittedTaskId, int userId) throws IOException {
        //删除提交记录
        if(submitTaskService.deleteSubmittedTask(submittedTaskId,userId)){
            ReturnTask returnTask = new ReturnTask(userId, submittedTaskId, 1, Timestamp.valueOf(LocalDateTime.now()));
            User user = userService.getUserById(userId);
            if(user.getEmailNumber()!=null) {
                //通知
                taskNoticeService.addTaskNotice(userId,submittedTaskId, TaskNotice.recallType,actionUserId);
            }
            //把打回写入打回表
            if(returnTaskMapper.isExist(returnTask)){
                //自增打回次数
                return returnTaskMapper.selfIncreasingReturnCount(returnTask)>0;
            }else {
                //插入新的记录
                return returnTaskMapper.insert(returnTask)>0;
            }
        }
        return false;
    }

    @Override
    public List<ReturnTask> getAllReturnTask(int submittedTaskId) {
        ReturnTaskExample returnTaskExample = new ReturnTaskExample();
        returnTaskExample.createCriteria().andTaskIdEqualTo(submittedTaskId);
        Set<Integer> submittedSetId = submitTaskService.getSubmittedStudent(submittedTaskId).stream().map(User::getId).collect(Collectors.toSet());
        return returnTaskMapper.selectByExample(returnTaskExample).stream().filter(returnTask -> !submittedSetId.contains(returnTask.getUserId())).collect(Collectors.toList());
    }

    @Override
    public ReturnTask getReturnTask(int submittedTaskId, int userId) {
        return returnTaskMapper.selectByPrimaryKey(new ReturnTaskKey(userId,submittedTaskId));
    }

    @Override
    public boolean batchReturnTask(int actionUserId, int submittedTaskId, Integer[] userIds) throws IOException {
        for(Integer userId:userIds){
            returnTask(actionUserId,submittedTaskId,userId);
        }
        return true;
    }
}
