package com.alpha.classpie.dao;

import com.alpha.classpie.pojo.ExternalLink;
import com.alpha.classpie.example.ExternalLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExternalLinkMapper {
    long countByExample(ExternalLinkExample example);

    int deleteByExample(ExternalLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExternalLink record);

    int insertSelective(ExternalLink record);

    List<ExternalLink> selectByExample(ExternalLinkExample example);

    ExternalLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExternalLink record, @Param("example") ExternalLinkExample example);

    int updateByExample(@Param("record") ExternalLink record, @Param("example") ExternalLinkExample example);

    int updateByPrimaryKeySelective(ExternalLink record);

    int updateByPrimaryKey(ExternalLink record);
}
