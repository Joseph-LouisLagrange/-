package com.alpha.classpie.dao;

import com.alpha.classpie.example.TeacherCustomMajorExample;
import com.alpha.classpie.pojo.user.TeacherCustomMajor;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherCustomMajorMapper {
    List<TeacherCustomMajor> getTeacherCustomMajorsByUserId(int userId);

    long countByExample(TeacherCustomMajorExample example);

    int deleteByExample(TeacherCustomMajorExample example);

    int deleteByPrimaryKey(TeacherCustomMajor key);

    int insert(TeacherCustomMajor record);

    int insertSelective(TeacherCustomMajor record);

    List<TeacherCustomMajor> selectByExample(TeacherCustomMajorExample example);

    int updateByExampleSelective(@Param("record") TeacherCustomMajor record, @Param("example") TeacherCustomMajorExample example);

    int updateByExample(@Param("record") TeacherCustomMajor record, @Param("example") TeacherCustomMajorExample example);
}
