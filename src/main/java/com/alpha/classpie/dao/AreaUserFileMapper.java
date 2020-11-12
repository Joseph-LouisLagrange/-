package com.alpha.classpie.dao;

import com.alpha.classpie.example.AreaUserFileExample;
import com.alpha.classpie.pojo.file.AreaUserFileKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaUserFileMapper {
    long countByExample(AreaUserFileExample example);

    int deleteByExample(AreaUserFileExample example);

    int deleteByPrimaryKey(AreaUserFileKey key);

    int insert(AreaUserFileKey record);

    int insertSelective(AreaUserFileKey record);

    List<AreaUserFileKey> selectByExample(AreaUserFileExample example);

    int updateByExampleSelective(@Param("record") AreaUserFileKey record, @Param("example") AreaUserFileExample example);

    int updateByExample(@Param("record") AreaUserFileKey record, @Param("example") AreaUserFileExample example);
}
