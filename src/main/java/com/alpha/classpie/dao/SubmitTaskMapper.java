package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.task.SubmitTask;
import com.alpha.classpie.example.SubmitTaskExample;
import com.alpha.classpie.pojo.task.SubmitTaskKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SubmitTaskMapper {
    long countByExample(SubmitTaskExample example);

    int deleteByExample(SubmitTaskExample example);

    int deleteByPrimaryKey(SubmitTaskKey key);

    int insert(SubmitTask record);

    int insertSelective(SubmitTask record);

    List<SubmitTask> selectByExample(SubmitTaskExample example);

    SubmitTask selectByPrimaryKey(SubmitTaskKey key);

    int updateByExampleSelective(@Param("record") SubmitTask record, @Param("example") SubmitTaskExample example);

    int updateByExample(@Param("record") SubmitTask record, @Param("example") SubmitTaskExample example);

    int updateByPrimaryKeySelective(SubmitTask record);

    int updateByPrimaryKey(SubmitTask record);
}
