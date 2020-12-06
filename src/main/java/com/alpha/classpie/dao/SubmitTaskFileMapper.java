package com.alpha.classpie.dao;

import com.alpha.classpie.example.SubmitTaskFileExample;
import com.alpha.classpie.pojo.file.SubmitTaskFile;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SubmitTaskFileMapper {
    long countByExample(SubmitTaskFileExample example);

    int deleteByExample(SubmitTaskFileExample example);

    int deleteByPrimaryKey(SubmitTaskFile key);

    int insert(SubmitTaskFile record);

    int insertSelective(SubmitTaskFile record);

    List<SubmitTaskFile> selectByExample(SubmitTaskFileExample example);

    int updateByExampleSelective(@Param("record") SubmitTaskFile record, @Param("example") SubmitTaskFileExample example);

    int updateByExample(@Param("record") SubmitTaskFile record, @Param("example") SubmitTaskFileExample example);
}
