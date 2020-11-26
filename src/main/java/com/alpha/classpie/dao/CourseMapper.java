package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.example.CourseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseMapper {
    long countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    Course getCourseByCode(String code);

    Integer getCourseIdByCode(String code);

    Integer updateArchive(@Param("archiveState") boolean archiveState,@Param("id") Integer id);

    List<Course> getCourses(@Param("userId") int userId,@Param("roleId") int roleId);

    int updateCourseCode(@Param("courseId") int  courseId,@Param("courseCode")String courseCode);
}
