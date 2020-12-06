package com.alpha.classpie.service.inf.notice;

import com.alpha.classpie.pojo.Notice;
import com.alpha.classpie.pojo.task.TaskNotice;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/21
 *
 */
@Service
public interface TaskNoticeService extends NoticeService {
    public boolean addTaskNotice(int userId,int taskId,String type,int actionUserId);
    public boolean addTaskNotice(List<Integer> userId,int taskId,String type,int actionUserId);
    public List<TaskNotice> getAllNoReadTaskNotice(int userId);
    public List<TaskNotice> getAllTaskNotice(int userId);
    public boolean markReadTaskNotice(int noticeId,int userId);
    public boolean deleteTaskNotice(int taskId);
}
