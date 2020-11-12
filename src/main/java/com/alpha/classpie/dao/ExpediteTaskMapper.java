package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.task.ExpediteTask;
import com.alpha.classpie.example.ExpediteTaskExample;
import com.alpha.classpie.pojo.task.ExpediteTaskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpediteTaskMapper {
    long countByExample(ExpediteTaskExample example);

    int deleteByExample(ExpediteTaskExample example);

    int deleteByPrimaryKey(ExpediteTaskKey key);

    int insert(ExpediteTask record);

    int insertSelective(ExpediteTask record);

    List<ExpediteTask> selectByExample(ExpediteTaskExample example);

    ExpediteTask selectByPrimaryKey(ExpediteTaskKey key);

    int updateByExampleSelective(@Param("record") ExpediteTask record, @Param("example") ExpediteTaskExample example);

    int updateByExample(@Param("record") ExpediteTask record, @Param("example") ExpediteTaskExample example);

    int updateByPrimaryKeySelective(ExpediteTask record);

    int updateByPrimaryKey(ExpediteTask record);
}
