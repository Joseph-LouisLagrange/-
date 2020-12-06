package com.alpha.classpie.dao;


import java.util.List;

import com.alpha.classpie.example.TaskCommentExample;
import com.alpha.classpie.pojo.comment.TaskComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskCommentMapper {
    long countByExample(TaskCommentExample example);

    int deleteByExample(TaskCommentExample example);

    int deleteByPrimaryKey(TaskComment key);

    int insert(TaskComment record);

    int insertSelective(TaskComment record);

    List<TaskComment> selectByExample(TaskCommentExample example);

    int updateByExampleSelective(@Param("record") TaskComment record, @Param("example") TaskCommentExample example);

    int updateByExample(@Param("record") TaskComment record, @Param("example") TaskCommentExample example);

    boolean isExist(TaskComment taskComment);

}
