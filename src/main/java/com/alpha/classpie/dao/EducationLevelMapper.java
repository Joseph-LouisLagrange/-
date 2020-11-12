package com.alpha.classpie.dao;

import com.alpha.classpie.dto.EducationTree;
import com.alpha.classpie.pojo.other.EducationLevel;
import com.alpha.classpie.example.EducationLevelExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EducationLevelMapper {
    long countByExample(EducationLevelExample example);

    int deleteByExample(EducationLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EducationLevel record);

    int insertSelective(EducationLevel record);

    List<EducationLevel> selectByExample(EducationLevelExample example);

    EducationLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EducationLevel record, @Param("example") EducationLevelExample example);

    int updateByExample(@Param("record") EducationLevel record, @Param("example") EducationLevelExample example);

    int updateByPrimaryKeySelective(EducationLevel record);

    int updateByPrimaryKey(EducationLevel record);

    List<EducationTree> findEducationTree();
}
