package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.bulletin.BulletinComment;
import com.alpha.classpie.example.BulletinCommentExample;
import com.alpha.classpie.pojo.bulletin.BulletinCommentKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BulletinCommentMapper {
    long countByExample(BulletinCommentExample example);

    int deleteByExample(BulletinCommentExample example);

    int deleteByPrimaryKey(BulletinCommentKey key);

    int insert(BulletinComment record);

    int insertSelective(BulletinComment record);

    List<BulletinComment> selectByExampleWithBLOBs(BulletinCommentExample example);

    List<BulletinComment> selectByExample(BulletinCommentExample example);

    BulletinComment selectByPrimaryKey(BulletinCommentKey key);

    int updateByExampleSelective(@Param("record") BulletinComment record, @Param("example") BulletinCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") BulletinComment record, @Param("example") BulletinCommentExample example);

    int updateByExample(@Param("record") BulletinComment record, @Param("example") BulletinCommentExample example);

    int updateByPrimaryKeySelective(BulletinComment record);

    int updateByPrimaryKeyWithBLOBs(BulletinComment record);

    int updateByPrimaryKey(BulletinComment record);
}
