package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.task.TaskCheckDuplicate;
import com.alpha.classpie.example.TaskCheckDuplicateExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskCheckDuplicateMapper {
    long countByExample(TaskCheckDuplicateExample example);

    int deleteByExample(TaskCheckDuplicateExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(TaskCheckDuplicate record);

    int insertSelective(TaskCheckDuplicate record);

    List<TaskCheckDuplicate> selectByExample(TaskCheckDuplicateExample example);

    TaskCheckDuplicate selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") TaskCheckDuplicate record, @Param("example") TaskCheckDuplicateExample example);

    int updateByExample(@Param("record") TaskCheckDuplicate record, @Param("example") TaskCheckDuplicateExample example);

    int updateByPrimaryKeySelective(TaskCheckDuplicate record);

    int updateByPrimaryKey(TaskCheckDuplicate record);
}
