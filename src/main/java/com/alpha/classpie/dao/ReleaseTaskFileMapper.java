package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.file.ReleaseTaskFile;
import com.alpha.classpie.example.ReleaseTaskFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReleaseTaskFileMapper {
    long countByExample(ReleaseTaskFileExample example);

    int deleteByExample(ReleaseTaskFileExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(ReleaseTaskFile record);

    int insertSelective(ReleaseTaskFile record);

    List<ReleaseTaskFile> selectByExample(ReleaseTaskFileExample example);

    ReleaseTaskFile selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") ReleaseTaskFile record, @Param("example") ReleaseTaskFileExample example);

    int updateByExample(@Param("record") ReleaseTaskFile record, @Param("example") ReleaseTaskFileExample example);

    int updateByPrimaryKeySelective(ReleaseTaskFile record);

    int updateByPrimaryKey(ReleaseTaskFile record);
}
