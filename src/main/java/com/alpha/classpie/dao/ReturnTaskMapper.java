package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.task.ReturnTask;
import com.alpha.classpie.example.ReturnTaskExample;
import com.alpha.classpie.pojo.task.ReturnTaskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnTaskMapper {
    long countByExample(ReturnTaskExample example);

    int deleteByExample(ReturnTaskExample example);

    int deleteByPrimaryKey(ReturnTaskKey key);

    int insert(ReturnTask record);

    int insertSelective(ReturnTask record);

    List<ReturnTask> selectByExample(ReturnTaskExample example);

    ReturnTask selectByPrimaryKey(ReturnTaskKey key);

    int updateByExampleSelective(@Param("record") ReturnTask record, @Param("example") ReturnTaskExample example);

    int updateByExample(@Param("record") ReturnTask record, @Param("example") ReturnTaskExample example);

    int updateByPrimaryKeySelective(ReturnTask record);

    int updateByPrimaryKey(ReturnTask record);
}
