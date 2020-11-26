package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.UserCourse;
import com.alpha.classpie.example.UserCourseExample;
import com.alpha.classpie.pojo.UserCourseKey;
import java.util.List;

import com.alpha.classpie.pojo.course.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserCourseMapper {
    long countByExample(UserCourseExample example);

    int deleteByExample(UserCourseExample example);

    int deleteByPrimaryKey(UserCourseKey key);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    List<UserCourse> selectByExample(UserCourseExample example);

    UserCourse selectByPrimaryKey(UserCourseKey key);

    int updateByExampleSelective(@Param("record") UserCourse record, @Param("example") UserCourseExample example);

    int updateByExample(@Param("record") UserCourse record, @Param("example") UserCourseExample example);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);

    int updateArchive(@Param("archiveState") boolean archiveState,@Param("userId") int userId,@Param("courseId") int courseId);

    Integer getMaxOrderByUserId(int userId);

    List<Course> getCoursesByUserId(int userId);
}
