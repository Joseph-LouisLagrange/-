package com.alpha.classpie.bcz;

import com.alpha.classpie.bcz.model.WordEnglishInterpretationExample;
import com.alpha.classpie.bcz.model.WordEnglishInterpretationKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WordEnglishInterpretationMapper {
    long countByExample(WordEnglishInterpretationExample example);

    int deleteByExample(WordEnglishInterpretationExample example);

    int deleteByPrimaryKey(WordEnglishInterpretationKey key);

    int insert(WordEnglishInterpretationKey record);

    int insertSelective(WordEnglishInterpretationKey record);

    List<WordEnglishInterpretationKey> selectByExample(WordEnglishInterpretationExample example);

    int updateByExampleSelective(@Param("record") WordEnglishInterpretationKey record, @Param("example") WordEnglishInterpretationExample example);

    int updateByExample(@Param("record") WordEnglishInterpretationKey record, @Param("example") WordEnglishInterpretationExample example);
}
