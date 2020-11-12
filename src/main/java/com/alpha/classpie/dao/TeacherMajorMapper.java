package com.alpha.classpie.dao;

import com.alpha.classpie.example.TeacherMajorExample;
import com.alpha.classpie.pojo.other.Major;
import com.alpha.classpie.pojo.user.TeacherMajor;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMajorMapper {
    long countByExample(TeacherMajorExample example);

    int deleteByExample(TeacherMajorExample example);

    int deleteByPrimaryKey(TeacherMajor key);

    int insert(TeacherMajor record);

    int insertSelective(TeacherMajor record);

    List<TeacherMajor> selectByExample(TeacherMajorExample example);

    int updateByExampleSelective(@Param("record") TeacherMajor record, @Param("example") TeacherMajorExample example);

    int updateByExample(@Param("record") TeacherMajor record, @Param("example") TeacherMajorExample example);

    List<Major> getMajorsByUserId(@Param("userId") int userId);
}
