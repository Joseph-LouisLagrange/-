package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.file.ReleaseTaskFile;
import com.alpha.classpie.example.ReleaseTaskFileExample;
import com.alpha.classpie.pojo.file.ReleaseTaskFileKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReleaseTaskFileMapper {
    long countByExample(ReleaseTaskFileExample example);

    int deleteByExample(ReleaseTaskFileExample example);

    int deleteByPrimaryKey(ReleaseTaskFileKey key);

    int insert(ReleaseTaskFile record);

    int insertSelective(ReleaseTaskFile record);

    List<ReleaseTaskFile> selectByExample(ReleaseTaskFileExample example);

    ReleaseTaskFile selectByPrimaryKey(ReleaseTaskFileKey key);

    int updateByExampleSelective(@Param("record") ReleaseTaskFile record, @Param("example") ReleaseTaskFileExample example);

    int updateByExample(@Param("record") ReleaseTaskFile record, @Param("example") ReleaseTaskFileExample example);

    int updateByPrimaryKeySelective(ReleaseTaskFile record);

    int updateByPrimaryKey(ReleaseTaskFile record);
}
