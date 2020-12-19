package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.task.ReturnTask;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public interface RecallTaskService {
    public boolean returnTask(int actionUserId,int submittedTaskId,int userId) throws IOException;
    public List<ReturnTask> getAllReturnTask(int submittedTaskId);
    public ReturnTask getReturnTask(int submittedTaskId,int userId);
    public boolean batchReturnTask(int actionUserId,int submittedTaskId, Integer[] userIds) throws IOException;
}
