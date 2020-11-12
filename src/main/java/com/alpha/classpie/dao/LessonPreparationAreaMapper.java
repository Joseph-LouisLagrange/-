package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.course.LessonPreparationArea;
import com.alpha.classpie.example.LessonPreparationAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LessonPreparationAreaMapper {
    long countByExample(LessonPreparationAreaExample example);

    int deleteByExample(LessonPreparationAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LessonPreparationArea record);

    int insertSelective(LessonPreparationArea record);

    List<LessonPreparationArea> selectByExample(LessonPreparationAreaExample example);

    LessonPreparationArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LessonPreparationArea record, @Param("example") LessonPreparationAreaExample example);

    int updateByExample(@Param("record") LessonPreparationArea record, @Param("example") LessonPreparationAreaExample example);

    int updateByPrimaryKeySelective(LessonPreparationArea record);

    int updateByPrimaryKey(LessonPreparationArea record);
}
