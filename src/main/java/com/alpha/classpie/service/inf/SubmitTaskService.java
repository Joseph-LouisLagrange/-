package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.file.SubmitTaskFile;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.User;
import org.jodconverter.core.office.OfficeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/16
 */
@Service
public interface SubmitTaskService {
    public SubmitTask submitTask(MultipartFile[] multipartFiles, int taskId, int userId) throws IOException;
    public SubmitTask getSubmitTask(int taskId,int userId);
    public boolean canUseFile(int taskId,int userId,int fileId);
    public List<SubmitTask> getSubmitTasks(int taskId,Integer[] userId);
    public List<SubmitTask> getSubmitTasks(int taskId);
    public List<Student> getSubmittedStudent(int taskId);
    public boolean isSubmitted(int userId,int taskId);
    public boolean correctSubmittedTask(int teacherId,int submittedTaskId,int userId,float score);
    public boolean deleteSubmittedTask(int submittedTaskId,int userId) throws IOException;
    public SubmitTask updateSubmitTask(int userId,int taskId,List<Integer> deleteFileIds,MultipartFile[] addNewMultipartFile) throws IOException;
    public void downAllSubmittedTask(int taskId, HttpServletResponse response) throws IOException;
    public void downSubmittedTaskFile(int fileId,HttpServletResponse response)throws IOException;
    public void previewSubmittedTaskFile(int fileId,HttpServletResponse response) throws IOException, OfficeException;
    public void downSomeSubmittedTask(int taskId,Integer[] userIds,HttpServletResponse response) throws IOException;
    public boolean batchCorrectSubmittedTask(Integer[] userIds,int submittedTaskId,int teacherId,float score);
}
