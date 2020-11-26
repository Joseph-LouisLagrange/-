package com.alpha.classpie.dto;


import com.alpha.classpie.dao.*;

import com.alpha.classpie.dto.warpper.*;
import com.alpha.classpie.example.*;
import com.alpha.classpie.pojo.UserCourse;
import com.alpha.classpie.pojo.UserCourseKey;
import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.task.ReturnTask;
import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.pojo.task.Task;
import com.alpha.classpie.pojo.user.*;
import com.alpha.classpie.service.inf.*;
import com.alpha.classpie.service.inf.comment.TaskCommentService;
import com.alpha.classpie.util.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/9
 */
@Component
public class DataWrapper {

    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskCommentService taskCommentService;

    @Autowired
    BulletinService bulletinService;

    @Autowired
    UserCourseMapper userCourseMapper;

    @Autowired
    ReleaseTaskFileMapper releaseTaskFileMapper;

    @Autowired
    SubmitTaskMapper submitTaskMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    SubmitTaskService submitTaskService;

    @Autowired
    SubmitTaskFileMapper submitTaskFileMapper;

    @Autowired
    TeacherCustomMajorMapper teacherCustomMajorMapper;

    @Autowired
    TeacherMajorMapper teacherMajorMapper;

    @Autowired
    TeacherTeachCourseMapper teacherTeachCourseMapper;

    @Autowired
    TeacherDepartmentMapper teacherDepartmentMapper;

    public CommentWrapper doCommentWrapper(Comment taskComment){
        CommentWrapper commentWrapper=new CommentWrapper();
        BeanUtils.copyProperties(taskComment,commentWrapper);
        List<CommentReplyWrapper> commentReplyWrappers = taskCommentService.getCommentReply(taskComment.getId()).stream().map(this::doCommentReplyWrapper).collect(Collectors.toList());
        //装载回复的数据
        commentWrapper.setCommentReplyList(commentReplyWrappers);
        //装载评论者的个人信息
        commentWrapper.setPublisherName(userService.getUserById(commentWrapper.getUserId()).getName());
        return commentWrapper;
    }

    public CommentReplyWrapper doCommentReplyWrapper(CommentReply commentReply){
        CommentReplyWrapper commentReplyWrapper = new CommentReplyWrapper();
        BeanUtils.copyProperties(commentReply,commentReplyWrapper);//赋值属性
        //装载发布者
        commentReplyWrapper.setPublisherName(userService.getUserById(commentReply.getUserId()).getName());
        return commentReplyWrapper;
    }



    public SubmitTaskWrapper doSubmitTaskWrapper(SubmitTask submitTask){
        SubmitTaskWrapper submitTaskWrapper = new SubmitTaskWrapper();
        BeanUtils.copyProperties(submitTask,submitTaskWrapper);
        //开始添加[增强属性]
        SubmitTaskFileExample submitTaskFileExample = new SubmitTaskFileExample();
        submitTaskFileExample.createCriteria().andTaskIdEqualTo(submitTask.getTaskId())
                .andUserIdEqualTo(submitTask.getUserId());
        submitTaskWrapper.setSubmitTaskFiles(submitTaskFileMapper.selectByExample(submitTaskFileExample));
        //加入 Student 信息的属性
        submitTaskWrapper.setStudent(userService.getStudentByUserId(submitTask.getUserId()));
        return submitTaskWrapper;
    }

    public CourseWrapper doCourseWrapper(Course course, int userId){
        CourseWrapper courseWrapper = new CourseWrapper();
        BeanUtils.copyProperties(course,courseWrapper);
        int courseId=course.getId();
        courseWrapper.setMemberCount(courseService.getCourseMemberCount(courseId));

        UserCourse userCourse = userCourseMapper.selectByPrimaryKey(new UserCourseKey(userId, courseId));
        BeanUtils.copyProperties(userCourse,courseWrapper);

        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andCourseIdEqualTo(courseId);
        courseWrapper.setTaskCount(taskMapper.countByExample(taskExample));

        //计算归档
        boolean isArchive=userCourse.getIsArchive()|| course.getIsArchive();
        courseWrapper.setIsArchive(isArchive);

        if(isArchive){
            //计算归档的范围
            courseWrapper.setIsArchiveAll(course.getIsArchive());
        }
        //计算最近的课程
        courseWrapper.setRecentTasks(taskService.page(new PageParam(1,2),taskExample).getList());
        return courseWrapper;
    }

    public UserSafeWrapper doUserSafeWrap(User user){
        UserSafeWrapper userSafeWrapper = new UserSafeWrapper();
        BeanUtils.copyProperties(user,userSafeWrapper);
        return userSafeWrapper;
    }

    public TeacherSafeWrapper doTeacherSafeWrap(User user){
        TeacherSafeWrapper teacherSafeWrapper = new TeacherSafeWrapper();
        BeanUtils.copyProperties(user,teacherSafeWrapper);
        Integer id = teacherSafeWrapper.getId();
        //加强属性
        teacherSafeWrapper.setMajors(teacherMajorMapper.getMajorsByUserId(id));
        teacherSafeWrapper.setTeacherTeachCourses(teacherTeachCourseMapper.getTeacherTeachCourseByUserId(id));
        teacherSafeWrapper.setTeacherDepartments(teacherDepartmentMapper.getDepartmentsByUserId(id));
        teacherSafeWrapper.setTeacherCustomMajors(teacherCustomMajorMapper.getTeacherCustomMajorsByUserId(id));
        return teacherSafeWrapper;
    }

    public StudentSafeWrapper doStudentSafeWrap(User user){
        StudentSafeWrapper userSafeWrapper = new StudentSafeWrapper();
        BeanUtils.copyProperties(user,userSafeWrapper);
        String studentId = studentMapper.selectByPrimaryKey(user.getId()).getStudentId();
        userSafeWrapper.setStudentId(studentId);
        return userSafeWrapper;
    }

    public StudentViewTaskWrapper doStudentViewTaskWrap(Task task,int userId){
        StudentViewTaskWrapper studentViewTaskWrapper=new StudentViewTaskWrapper();
        BeanUtils.copyProperties(task,studentViewTaskWrapper);//赋值属性
        Integer taskId = task.getId();
        studentViewTaskWrapper.setCommentCount(taskCommentService.getTaskCommentCount(taskId));
        studentViewTaskWrapper.setSubmit(submitTaskService.isSubmitted(userId,taskId));
        studentViewTaskWrapper.setReleaseTaskFiles(taskService.getReleaseTaskFiles(taskId));
        return studentViewTaskWrapper;
    }


    public TeacherViewTaskWrapper doTeacherViewTaskWrap(Task task){
        TeacherViewTaskWrapper teacherViewTaskWrapper = new TeacherViewTaskWrapper();
        BeanUtils.copyProperties(task, teacherViewTaskWrapper);

        Integer taskId = task.getId();
        //加入发布的文件
        teacherViewTaskWrapper.setReleaseTaskFiles(taskService.getReleaseTaskFiles(taskId));

        long courseMemberCount = courseService.getCourseMemberCount(task.getCourseId());//计算总的班级人数

        SubmitTaskExample submitTaskExample = new SubmitTaskExample();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId).andIsCorrectEqualTo(false);
        teacherViewTaskWrapper.setNotCorrectedCount(submitTaskMapper.countByExample(submitTaskExample));//设置未批改的数量
        submitTaskExample.clear();
        submitTaskExample.createCriteria().andTaskIdEqualTo(taskId).andIsCorrectEqualTo(true);
        teacherViewTaskWrapper.setCorrectedCount(submitTaskMapper.countByExample(submitTaskExample));//设置已批改的数量
        //设置未交的人数量
        teacherViewTaskWrapper.setUnpaidCount(courseMemberCount- teacherViewTaskWrapper.getCorrectedCount()- teacherViewTaskWrapper.getNotCorrectedCount());
        //设置评论总数
        teacherViewTaskWrapper.setCommentCount(taskCommentService.getTaskCommentCount(taskId));
        return teacherViewTaskWrapper;
    }

    public ReturnTaskWrapper doReturnTaskWrap(ReturnTask returnTask){
        ReturnTaskWrapper returnTaskWrapper = new ReturnTaskWrapper();
        BeanUtils.copyProperties(returnTask,returnTaskWrapper);
        //加入强化的属性
        returnTaskWrapper.setStudent(this.doStudentSafeWrap(userService.getUserById(returnTask.getUserId())));
        return returnTaskWrapper;
    }


    public BulletinWrapper doBulletinWrap(Bulletin bulletin){
        BulletinWrapper bulletinWrapper = new BulletinWrapper();
        BeanUtils.copyProperties(bulletin,bulletinWrapper);
        //设置 已读 数量
        bulletinWrapper.setReadCount(bulletinService.getReadCount(bulletin.getId()));
        //设置发布者
        bulletinWrapper.setPublisher(this.doUserSafeWrap(userService.getUserById(bulletin.getPublisherId())));
        //设置评论数量
        bulletinWrapper.setCommentCount(bulletinService.getCommentCount(bulletin.getId()));
        return bulletinWrapper;
    }

    public BulletinDetailWrapper doBulletinDetailWrap(Bulletin bulletin){
        BulletinWrapper bulletinWrapper = this.doBulletinWrap(bulletin);
        BulletinDetailWrapper bulletinDetailWrapper = new BulletinDetailWrapper();
        BeanUtils.copyProperties(bulletinWrapper,bulletinDetailWrapper);

        Integer bulletinId = bulletin.getId();

        List<CommentWrapper> commentWrappers = bulletinService.getBulletinComments(bulletinId).stream().map(this::doCommentWrapper).collect(Collectors.toList());
        bulletinDetailWrapper.setCommentWrappers(commentWrappers);//设置评论的详细数据
        return bulletinDetailWrapper;
    }
}
