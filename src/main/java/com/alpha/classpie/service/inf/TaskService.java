package com.alpha.classpie.service.inf;

import com.alpha.classpie.example.TaskExample;
import com.alpha.classpie.pojo.file.ReleaseTaskFile;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Service
public interface TaskService extends BaseService<Task, TaskExample>{
    public Task releaseDoCheckDuplicateTask(Task task, MultipartFile[] multipartFiles, int userId) throws IOException;
    public Task releaseNoCheckDuplicateTask(Task task, MultipartFile[] multipartFiles, int userId) throws IOException;
    public List<Task> getReleasedTasks(int courseId);
    public Task simpleEditTask(Task task, int taskId);
    public Task powerEditTask(Task task,int taskId,MultipartFile[] addNewMultipartFiles,List<Integer> deleteReleasedTaskFileId) throws IOException;
    public boolean deleteTask(int taskId) throws IOException;
    public void downloadAllTask(int courseId,String emailAddress);
    public List<User> getUnpaidStudents(int taskId);
    public Task getTaskById(int id);
    public boolean canUse(int userId,int taskId);
    public void downTaskFile(int fileId, HttpServletResponse httpServletResponse) throws IOException;
    public List<ReleaseTaskFile> getReleaseTaskFiles(int taskId);
    public boolean expediteTask(int teacherId,int taskId,int toUserId);
    public boolean batchExpediteTask(int teacherId,int taskId,int[] toUserId);
    public long getExpediteCount(int taskId,int userId);
}
