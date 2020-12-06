package com.alpha.classpie.bcz;

import com.alpha.classpie.bcz.model.WordPhoneticTranscriptionExample;
import com.alpha.classpie.bcz.model.WordPhoneticTranscription;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WordPhoneticTranscriptionMapper {
    long countByExample(WordPhoneticTranscriptionExample example);

    int deleteByExample(WordPhoneticTranscriptionExample example);

    int deleteByPrimaryKey(WordPhoneticTranscription key);

    int insert(WordPhoneticTranscription record);

    int insertSelective(WordPhoneticTranscription record);

    List<WordPhoneticTranscription> selectByExample(WordPhoneticTranscriptionExample example);

    int updateByExampleSelective(@Param("record") WordPhoneticTranscription record, @Param("example") WordPhoneticTranscriptionExample example);

    int updateByExample(@Param("record") WordPhoneticTranscription record, @Param("example") WordPhoneticTranscriptionExample example);
}
