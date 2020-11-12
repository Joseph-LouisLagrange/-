package com.alpha.classpie.dao;

import com.alpha.classpie.example.BulletinReadExample;
import com.alpha.classpie.pojo.bulletin.BulletinReadKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BulletinReadMapper {
    long countByExample(BulletinReadExample example);

    int deleteByExample(BulletinReadExample example);

    int deleteByPrimaryKey(BulletinReadKey key);

    int insert(BulletinReadKey record);

    int insertSelective(BulletinReadKey record);

    List<BulletinReadKey> selectByExample(BulletinReadExample example);

    int updateByExampleSelective(@Param("record") BulletinReadKey record, @Param("example") BulletinReadExample example);

    int updateByExample(@Param("record") BulletinReadKey record, @Param("example") BulletinReadExample example);
}
