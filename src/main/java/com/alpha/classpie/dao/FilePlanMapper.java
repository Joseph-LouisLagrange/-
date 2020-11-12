package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.example.FilePlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilePlanMapper {
    long countByExample(FilePlanExample example);

    int deleteByExample(FilePlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FilePlan record);

    int insertSelective(FilePlan record);

    List<FilePlan> selectByExample(FilePlanExample example);

    FilePlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FilePlan record, @Param("example") FilePlanExample example);

    int updateByExample(@Param("record") FilePlan record, @Param("example") FilePlanExample example);

    int updateByPrimaryKeySelective(FilePlan record);

    int updateByPrimaryKey(FilePlan record);
}
