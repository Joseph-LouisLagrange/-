package com.alpha.classpie.bcz;

import com.alpha.classpie.bcz.model.GraphicExampleSentences;
import com.alpha.classpie.bcz.model.GraphicExampleSentencesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GraphicExampleSentencesMapper {
    long countByExample(GraphicExampleSentencesExample example);

    int deleteByExample(GraphicExampleSentencesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GraphicExampleSentences record);

    int insertSelective(GraphicExampleSentences record);

    List<GraphicExampleSentences> selectByExample(GraphicExampleSentencesExample example);

    GraphicExampleSentences selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GraphicExampleSentences record, @Param("example") GraphicExampleSentencesExample example);

    int updateByExample(@Param("record") GraphicExampleSentences record, @Param("example") GraphicExampleSentencesExample example);

    int updateByPrimaryKeySelective(GraphicExampleSentences record);

    int updateByPrimaryKey(GraphicExampleSentences record);
}
