package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.user.TeacherTeachCourse;
import com.alpha.classpie.example.TeacherTeachCourseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherTeachCourseMapper {
    List<TeacherTeachCourse> getTeacherTeachCourseByUserId(int userId);

    long countByExample(TeacherTeachCourseExample example);

    int deleteByExample(TeacherTeachCourseExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TeacherTeachCourse record);

    int insertSelective(TeacherTeachCourse record);

    List<TeacherTeachCourse> selectByExample(TeacherTeachCourseExample example);

    TeacherTeachCourse selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") TeacherTeachCourse record, @Param("example") TeacherTeachCourseExample example);

    int updateByExample(@Param("record") TeacherTeachCourse record, @Param("example") TeacherTeachCourseExample example);

    int updateByPrimaryKeySelective(TeacherTeachCourse record);

    int updateByPrimaryKey(TeacherTeachCourse record);
}
