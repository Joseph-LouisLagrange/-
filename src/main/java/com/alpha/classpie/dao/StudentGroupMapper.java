package com.alpha.classpie.dao;

import com.alpha.classpie.example.StudentGroupExample;
import com.alpha.classpie.pojo.user.StudentGroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentGroupMapper {
    long countByExample(StudentGroupExample example);

    int deleteByExample(StudentGroupExample example);

    int deleteByPrimaryKey(StudentGroupKey key);

    int insert(StudentGroupKey record);

    int insertSelective(StudentGroupKey record);

    List<StudentGroupKey> selectByExample(StudentGroupExample example);

    int updateByExampleSelective(@Param("record") StudentGroupKey record, @Param("example") StudentGroupExample example);

    int updateByExample(@Param("record") StudentGroupKey record, @Param("example") StudentGroupExample example);
}
