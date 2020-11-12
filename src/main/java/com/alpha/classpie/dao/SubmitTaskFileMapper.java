package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.file.SubmitTaskFile;
import com.alpha.classpie.example.SubmitTaskFileExample;
import com.alpha.classpie.pojo.file.SubmitTaskFileKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubmitTaskFileMapper {
    long countByExample(SubmitTaskFileExample example);

    int deleteByExample(SubmitTaskFileExample example);

    int deleteByPrimaryKey(SubmitTaskFileKey key);

    int insert(SubmitTaskFile record);

    int insertSelective(SubmitTaskFile record);

    List<SubmitTaskFile> selectByExample(SubmitTaskFileExample example);

    SubmitTaskFile selectByPrimaryKey(SubmitTaskFileKey key);

    int updateByExampleSelective(@Param("record") SubmitTaskFile record, @Param("example") SubmitTaskFileExample example);

    int updateByExample(@Param("record") SubmitTaskFile record, @Param("example") SubmitTaskFileExample example);

    int updateByPrimaryKeySelective(SubmitTaskFile record);

    int updateByPrimaryKey(SubmitTaskFile record);
}
