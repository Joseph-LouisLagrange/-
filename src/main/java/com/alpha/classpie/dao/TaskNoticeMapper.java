package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.task.TaskNotice;
import com.alpha.classpie.example.TaskNoticeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskNoticeMapper {
    long countByExample(TaskNoticeExample example);

    int deleteByExample(TaskNoticeExample example);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(TaskNotice record);

    int insertSelective(TaskNotice record);

    List<TaskNotice> selectByExample(TaskNoticeExample example);

    TaskNotice selectByPrimaryKey(Integer noticeId);

    int updateByExampleSelective(@Param("record") TaskNotice record, @Param("example") TaskNoticeExample example);

    int updateByExample(@Param("record") TaskNotice record, @Param("example") TaskNoticeExample example);

    int updateByPrimaryKeySelective(TaskNotice record);

    int updateByPrimaryKey(TaskNotice record);

    List<TaskNotice> getNoReadTaskNoticeByUserId(int userId);

    List<TaskNotice> getAllTaskNoticeByUserId(int userId);
}
