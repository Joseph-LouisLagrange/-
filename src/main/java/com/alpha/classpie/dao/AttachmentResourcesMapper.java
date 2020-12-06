package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.file.AttachmentResources;
import com.alpha.classpie.example.AttachmentResourcesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AttachmentResourcesMapper {
    long countByExample(AttachmentResourcesExample example);

    int deleteByExample(AttachmentResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttachmentResources record);

    int insertSelective(AttachmentResources record);

    List<AttachmentResources> selectByExample(AttachmentResourcesExample example);

    AttachmentResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttachmentResources record, @Param("example") AttachmentResourcesExample example);

    int updateByExample(@Param("record") AttachmentResources record, @Param("example") AttachmentResourcesExample example);

    int updateByPrimaryKeySelective(AttachmentResources record);

    int updateByPrimaryKey(AttachmentResources record);

    int selfIncreasingDownloadCount(Integer id);

    List<AttachmentResources> getChildren(@Param("courseId") Integer courseId,@Param("userFileId") Integer userFileId);

}
