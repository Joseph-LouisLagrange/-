package com.alpha.classpie.dao;

import com.alpha.classpie.example.BulletinCommentExample;
import com.alpha.classpie.pojo.comment.BulletinComment;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BulletinCommentMapper {
    long countByExample(BulletinCommentExample example);

    int deleteByExample(BulletinCommentExample example);

    int deleteByPrimaryKey(BulletinComment key);

    int insert(BulletinComment record);

    int insertSelective(BulletinComment record);

    List<BulletinComment> selectByExample(BulletinCommentExample example);

    int updateByExampleSelective(@Param("record") BulletinComment record, @Param("example") BulletinCommentExample example);

    int updateByExample(@Param("record") BulletinComment record, @Param("example") BulletinCommentExample example);
}
