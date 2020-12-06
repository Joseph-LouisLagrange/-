package com.alpha.classpie.bcz;

import com.alpha.classpie.bcz.model.TranslationExample;
import com.alpha.classpie.bcz.model.Translation;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TranslationMapper {
    long countByExample(TranslationExample example);

    int deleteByExample(TranslationExample example);

    int deleteByPrimaryKey(Translation key);

    int insert(Translation record);

    int insertSelective(Translation record);

    List<Translation> selectByExample(TranslationExample example);

    int updateByExampleSelective(@Param("record") Translation record, @Param("example") TranslationExample example);

    int updateByExample(@Param("record") Translation record, @Param("example") TranslationExample example);
}
