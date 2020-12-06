package com.alpha.classpie.dao;

import com.alpha.classpie.example.TeacherTeachCourseExample;
import com.alpha.classpie.pojo.user.TeacherTeachCourse;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherTeachCourseMapper {
    long countByExample(TeacherTeachCourseExample example);

    int deleteByExample(TeacherTeachCourseExample example);

    int deleteByPrimaryKey(TeacherTeachCourse key);

    List<TeacherTeachCourse> getTeacherTeachCourseByUserId(int userId);

    int insert(TeacherTeachCourse record);

    int insertSelective(TeacherTeachCourse record);

    List<TeacherTeachCourse> selectByExample(TeacherTeachCourseExample example);

    int updateByExampleSelective(@Param("record") TeacherTeachCourse record, @Param("example") TeacherTeachCourseExample example);

    int updateByExample(@Param("record") TeacherTeachCourse record, @Param("example") TeacherTeachCourseExample example);
}
