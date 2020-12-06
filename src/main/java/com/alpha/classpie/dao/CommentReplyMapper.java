package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.comment.CommentReply;
import com.alpha.classpie.example.CommentReplyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentReplyMapper {
    long countByExample(CommentReplyExample example);

    int deleteByExample(CommentReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentReply record);

    int insertSelective(CommentReply record);

    List<CommentReply> selectByExampleWithBLOBs(CommentReplyExample example);

    List<CommentReply> selectByExample(CommentReplyExample example);

    CommentReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    int updateByExample(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    int updateByPrimaryKeySelective(CommentReply record);

    int updateByPrimaryKeyWithBLOBs(CommentReply record);

    int updateByPrimaryKey(CommentReply record);
}
