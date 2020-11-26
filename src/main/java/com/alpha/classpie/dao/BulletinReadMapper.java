package com.alpha.classpie.dao;

import com.alpha.classpie.example.BulletinReadExample;
import com.alpha.classpie.pojo.bulletin.BulletinRead;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface BulletinReadMapper {
    long countByExample(BulletinReadExample example);

    int deleteByExample(BulletinReadExample example);

    int deleteByPrimaryKey(BulletinRead key);

    int insert(BulletinRead record);

    int insertSelective(BulletinRead record);

    List<BulletinRead> selectByExample(BulletinReadExample example);

    int updateByExampleSelective(@Param("record") BulletinRead record, @Param("example") BulletinReadExample example);

    int updateByExample(@Param("record") BulletinRead record, @Param("example") BulletinReadExample example);

}
