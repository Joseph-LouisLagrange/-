package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.SubmitTaskFileMapper;
import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.dto.warpper.SubmitTaskTeacherViewWrapper;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.pojo.file.SubmitTaskFile;
import com.alpha.classpie.pojo.file.UserFile;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.service.inf.*;
import com.alpha.classpie.util.file.EntryNode;
import com.alpha.classpie.util.file.ParentEntryNode;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service("defaultFileExportService")
public class FileExportServiceImpl implements FileExportService {

    @Autowired
    SubmitTaskFileMapper submitTaskFileMapper;

    @Autowired
    TaskService taskService;

    @Autowired
    UserFileService userFileService;

    @Autowired
    CourseService courseService;

    @Autowired
    FilePlanService filePlanService;

    @Autowired
    DataWrapper dataWrapper;      //这是一个败笔！！！！！！！（这里是不该引入的）

    @Autowired
    SubmitTaskService submitTaskService;

    private String getCourseExportName(Course course){
        return course.getClassName()+"-"+course.getName();
    }

    private String getTaskExportName(Task task){
        Course course=courseService.getCourseById(task.getCourseId());
        return getCourseExportName(course)+" "+task.getName()+" "+DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
    }

    private String getStudentExportName(Student student){
        return student.getId()+":"+student.getStudentId()+"-"+student.getName();
    }


    @Override
    public EntryNode exportAllSubmittedTask(int taskId) {
        Task task = taskService.getTaskById(taskId);
        List<SubmitTask> submitTasks = submitTaskService.getSubmitTasks(taskId);
        return internalExportSubmittedTask(task,submitTasks);
    }

    public EntryNode internalExportSubmittedTask(Task task,List<SubmitTask> submitTasks){
        ParentEntryNode currentNode = EntryNode.ROOT(getTaskExportName(task));
        List<SubmitTaskTeacherViewWrapper> submitWrappers = submitTasks.stream().map(dataWrapper::doSubmitTaskTeacherViewWrap).collect(Collectors.toList());
        for(SubmitTaskTeacherViewWrapper submitTaskTeacherViewWrapper : submitWrappers){
            currentNode = currentNode.createParentEntryNode(getStudentExportName(submitTaskTeacherViewWrapper.getStudent()));
            //currentNode 当前节点在学生文件夹下面,直接加入文件
            List<SubmitTaskFile> submitTaskFiles = submitTaskTeacherViewWrapper.getSubmitTaskFiles();
            for(SubmitTaskFile submitTaskFile: submitTaskFiles){
                FilePlan filePlan = submitTaskFile.getFilePlan();
                //放入作业
                currentNode.addFile(filePlan.getName(),filePlanService.getFile(filePlan));
            }
            //返回上一级节点
            currentNode=currentNode.returnParentEntryNode();
        }
        return currentNode;
    }

    @Override
    public EntryNode exportSubmittedTask(int taskId, Integer[] userIds) {
        Task task = taskService.getTaskById(taskId);
        List<SubmitTask> submitTasks = submitTaskService.getSubmitTasks(taskId, userIds);
        return internalExportSubmittedTask(task,submitTasks);
    }

    @Override
    public EntryNode exportUserFile(int id) {
        UserFile userFile = userFileService.getUserFile(id);
        if (userFile.getIsFolder()) {
            ParentEntryNode root = EntryNode.ROOT(userFile.getName());
            exportUserFileByDfs(id,root);
            return root;
        }
        return null;
    }


    private void  exportUserFileByDfs(int id,EntryNode entryNode){
        List<UserFile> children = userFileService.getChildren(id);
        if(children!=null) {
            ParentEntryNode parentEntryNode= (ParentEntryNode) entryNode;
            for (UserFile u : children) {
                if (u.getIsFolder()) {
                    //递归
                    exportUserFileByDfs(u.getId(), parentEntryNode.createParentEntryNode(u.getName()));
                } else {
                    //单文件
                    FilePlan filePlan = u.getFilePlan();
                    parentEntryNode.addFile(filePlan.getName(),filePlanService.getFile(filePlan));//加入文件
                }
            }
        }
    }


}
